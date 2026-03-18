package com.wanted.only_one.payments;

import java.util.List;
import java.util.Scanner;

public class PaymentInputView {

    private final PaymentController paycontroller;
    private final PaymentOutputView payoutputView;
    private final Scanner sc = new Scanner(System.in);
    private PaymentInputView PayController;

    public PaymentInputView(PaymentController paycontroller, PaymentOutputView payoutputView) {
        this.paycontroller = paycontroller;
        this.payoutputView = payoutputView;
    }

    public void showPayment() {
        payoutputView.printMessage("---결제 내역 조회---");

        List<PaymentDTO> payList = paycontroller.findMyPayment();
        payoutputView.printPayments(payList);
    }
}
