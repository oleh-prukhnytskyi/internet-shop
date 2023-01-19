package com.olegpruh.internetshop.service.mapper;

import com.olegpruh.internetshop.model.Order;
import com.olegpruh.internetshop.model.Transaction;
import com.olegpruh.internetshop.service.OrderService;
import com.olegpruh.internetshop.util.StringUtil;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;

@Component
public class TransactionMapper {
    private final OrderService orderService;

    public TransactionMapper(OrderService orderService) {
        this.orderService = orderService;
    }

    public Transaction paymentIntentToTransaction(PaymentIntent paymentIntent, Order order) throws StripeException {
        Transaction transaction = new Transaction();
        PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentIntent.getPaymentMethod());
        //Invoice invoice = Invoice.retrieve(paymentIntent.getInvoice());
        transaction.setTransactionId(paymentIntent.getId());
        transaction.setTransactionDate(Instant.ofEpochSecond(paymentIntent
                .getCreated()).atZone(ZoneId.systemDefault()).toLocalDate());
        transaction.setAmount(BigDecimal.valueOf(paymentIntent.getAmount()).divide(BigDecimal.valueOf(100)));
        transaction.setStatus(paymentIntent.getStatus());
        transaction.setOrder(orderService.getById(order.getId()));
        transaction.setCardNumber(Integer.parseInt(paymentMethod.getCard().getLast4()));
        transaction.setMethod(StringUtil.capitalize(paymentMethod.getCard().getBrand()));
        //transaction.setFee(BigDecimal.valueOf(invoice.getApplicationFeeAmount()));
        //transaction.setInvoice(invoice.getHostedInvoiceUrl());
        return transaction;
    }
}
