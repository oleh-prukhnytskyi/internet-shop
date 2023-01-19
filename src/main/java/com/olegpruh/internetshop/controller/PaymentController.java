package com.olegpruh.internetshop.controller;

import com.olegpruh.internetshop.model.*;
import com.olegpruh.internetshop.service.*;
import com.olegpruh.internetshop.service.mapper.OrderContentMapper;
import com.olegpruh.internetshop.service.mapper.TransactionMapper;
import com.olegpruh.internetshop.util.AuthenticationUtil;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Value("${stripe.key.public}")
    private String API_PUBLIC_KEY;
    private final CartService cartService;
    private final CustomerService customerService;
    private final StripeService stripeService;
    private final OrderService orderService;
    private final OrderContentService orderContentService;
    private final TransactionMapper transactionMapper;
    private final OrderContentMapper orderContentMapper;
    private final TransactionService transactionService;

    @Autowired
    public PaymentController(CartService cartService, CustomerService customerService,
                             StripeService stripeService, OrderService orderService,
                             OrderContentService orderContentService, TransactionMapper transactionMapper,
                             OrderContentMapper orderContentMapper, TransactionService transactionService) {
        this.cartService = cartService;
        this.customerService = customerService;
        this.stripeService = stripeService;
        this.orderService = orderService;
        this.orderContentService = orderContentService;
        this.transactionMapper = transactionMapper;
        this.orderContentMapper = orderContentMapper;
        this.transactionService = transactionService;
    }

    @GetMapping
    public String payment(Model model) throws StripeException {
        String user_email = AuthenticationUtil.getAuthentication().getName();
        List<Cart> carts = cartService.findAllByUserEmail(user_email);
        if (carts.isEmpty()) {
            return "redirect:/cart";
        }
        BigDecimal totalPrice = cartService.getTotalPrice(carts);
        Customer customer = customerService.findByUserEmail(user_email);
        model.addAttribute("total_price", totalPrice);
        model.addAttribute("customer", customer);
        model.addAttribute("products", cartService.findAllByUserEmail(user_email));
        model.addAttribute("page_title", "Payment");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);

        PaymentIntent paymentIntent = stripeService.createPaymentIntent(totalPrice.add(
                customer.getDeliveryOption().getPrice()).setScale(2).multiply(BigDecimal.valueOf(100)).longValue());
        model.addAttribute("client_secret", paymentIntent.getClientSecret());
        return "payment";
    }

    @PostMapping("/data")
    @ResponseBody
    public Long createCharge(@RequestParam String id) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        String user_email = AuthenticationUtil.getAuthentication().getName();
        Order order = orderService.save(orderService.generateOrder(user_email));
        List<OrderContent> orderContentStream = cartService.findAllByUserEmail(user_email).stream()
                .map(e -> orderContentMapper.cartToOrderContent(e, order)).collect(Collectors.toList());
        transactionService.save(transactionMapper.paymentIntentToTransaction(paymentIntent, order));
        orderContentStream.forEach(orderContentService::save);
        cartService.deleteAllByUserEmail(user_email);
        return order.getId();
    }

    @GetMapping ("{id}/success")
    public String success(Model model, @PathVariable Long id) {
        Order order = orderService.getById(id);
        model.addAttribute("page_title", "Payment");
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        model.addAttribute("order", order);
        model.addAttribute("transaction", transactionService.findByOrderId(order.getId()));
        return "payment_success";
    }

    @GetMapping("/invoice")
    public ResponseEntity<Resource> viewPdf(@PathVariable String invoice) throws StripeException {
        Resource resource = (Resource) new ByteArrayResource(Invoice.retrieve(invoice).getInvoicePdf().getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

}
