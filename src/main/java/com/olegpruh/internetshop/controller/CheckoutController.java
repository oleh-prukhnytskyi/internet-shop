package com.olegpruh.internetshop.controller;

import com.olegpruh.internetshop.model.*;
import com.olegpruh.internetshop.model.dto.CheckoutDto;
import com.olegpruh.internetshop.model.dto.CustomerDto;
import com.olegpruh.internetshop.service.*;
import com.olegpruh.internetshop.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final CartService cartService;
    private final AddressService addressService;
    private final CustomerService customerService;
    private final UserService userService;
    private final DeliveryOptionService deliveryOptionService;

    @Autowired
    public CheckoutController(CartService cartService, AddressService addressService,
                              CustomerService customerService, UserService userService,
                              DeliveryOptionService deliveryOptionService, OrderService orderService) {
        this.cartService = cartService;
        this.addressService = addressService;
        this.customerService = customerService;
        this.userService = userService;
        this.deliveryOptionService = deliveryOptionService;
    }

    @GetMapping
    public String checkout(Model model) {
        String user_email = AuthenticationUtil.getAuthentication().getName();
        List<Cart> carts = cartService.findAllByUserEmail(user_email);
        if (carts.isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("delivery_options", deliveryOptionService.findAll());
        model.addAttribute("products", carts);
        model.addAttribute("total_price", cartService.getTotalPrice(carts));
        model.addAttribute("addresses", addressService.findAllByUserEmail(user_email));
        model.addAttribute("page_title", "Checkout");
        model.addAttribute("customer", customerService.findByUserEmail(user_email));
        model.addAttribute("user", AuthenticationUtil.getAuthentication());
        return "checkout";
    }

    @PostMapping("/validate")
    @ResponseBody
    public List<ObjectError> validate(@ModelAttribute @Valid CustomerDto customerDto,
                                          BindingResult bindingResult) {
        return bindingResult.getAllErrors();
    }

    @PostMapping("/validate/new")
    @ResponseBody
    public List<ObjectError> validateNewAddress(@ModelAttribute @Valid CheckoutDto checkoutDto,
                                          BindingResult bindingResult) {
        return bindingResult.getAllErrors();
    }

    @PostMapping
    public String checkoutData(@ModelAttribute CheckoutDto checkoutDto) {
        Customer customer = new Customer(checkoutDto.getFirstName(), checkoutDto.getLastName(),
                checkoutDto.getPhone(), checkoutDto.getEmail());
        User user = userService.findByEmail(AuthenticationUtil.getAuthentication().getName()).get();
        customer.setUser(user);
        customer.setDeliveryOption(deliveryOptionService.getById(checkoutDto.getShipping()));
        Address address = new Address(checkoutDto.getAddress(), checkoutDto.getCity(),
                checkoutDto.getHouse(), checkoutDto.getPostcode());
        if (checkoutDto.getAddressOption().equals("new") && checkoutDto.isSaveAddress()) {
            address.setUser(user);
            customer.setAddress(addressService.save(address));
        } else if (checkoutDto.getAddressOption().equals("new")) {
            customer.setAddress(addressService.save(address));
        } else {
            customer.setAddress(addressService.getById(Long.valueOf(checkoutDto.getAddressOption())));
        }
        Customer byUserEmail = customerService.findByUserEmail(user.getEmail());
        if (byUserEmail != null) {
            customerService.update(byUserEmail.getId(), customer.getFirstName(), customer.getLastName(),
                    customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getDeliveryOption());
        } else {
            customerService.save(customer);
        }
        return "redirect:/payment";
    }
}
