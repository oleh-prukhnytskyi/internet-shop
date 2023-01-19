package com.olegpruh.internetshop.service.impl;

import com.olegpruh.internetshop.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StripeServiceImpl implements StripeService {
    @Value("${stripe.key.secret}")
    private String API_SECRET_KEY;

    @Override
    public PaymentIntent createPaymentIntent(Long amount) throws StripeException {
        Stripe.apiKey = API_SECRET_KEY;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "gbp");
        params.put("payment_method_types", List.of("card"));

        return PaymentIntent.create(params);
    }
}
