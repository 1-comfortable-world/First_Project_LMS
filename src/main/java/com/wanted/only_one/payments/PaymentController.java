package com.wanted.only_one.payments;

import java.util.List;

public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public List<PaymentDTO> findMyPayment() {
        return paymentService.findMyPayment();
    }
}
