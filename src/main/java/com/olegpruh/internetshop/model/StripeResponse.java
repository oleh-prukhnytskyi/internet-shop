package com.olegpruh.internetshop.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StripeResponse {
    private boolean status;
    private String details;

    public StripeResponse() {
        super();
        this.status = true;
    }

    public StripeResponse(boolean status, String details) {
        super();
        this.status = status;
        this.details = details;
    }
}
