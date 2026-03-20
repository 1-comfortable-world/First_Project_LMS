package com.wanted.only_one.payments;

import java.sql.SQLException;
import java.util.List;

public class PaymentController {

    public final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;

    }

    public static boolean payMoney(String email) {
        return PaymentService.payingMoney(email);
    }


    public List<PaymentDTO> findMyPayment(String payEmail) {
        return paymentService.findMyPayment(payEmail);
    }

    public boolean checkEmail(String value) throws SQLException {
        return paymentService.checkEmail(value);
    }
}
