package com.wanted.only_one.payments;

import com.wanted.only_one.member.view.MemberInputView;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PaymentInputView {

    private MemberInputView memberInputView;
    private PaymentController paycontroller;
    private PaymentOutputView payoutputView;
    private final Scanner sc = new Scanner(System.in);
    private PaymentInputView paymentInputView;

    public PaymentInputView(PaymentController paycontroller, PaymentOutputView payoutputView) {
        this.paycontroller = paycontroller;
        this.payoutputView = payoutputView;
    }

    public void pay() {
        payoutputView.printMessage("==========결제 금액은 29900원 입니다.==========");
        while(true) {
            System.out.println();
            System.out.println("==========---결제를 하시겠습니까?---==========");
            System.out.println("1. 결제하기");
            System.out.println("2. 뒤로 가기");
            System.out.print("번호를 입력해 주세요 : ");

            int option = inputInt();

            switch(option) {
                case 1:
                    if(payMoney()){
                        return;
                    }
                    break;
                case 2:
                    return;
                default:
                    payoutputView.printError("숫자를 제대로 입력하세요.");
            }

        }
    }

    private boolean payMoney() {
        System.out.println(" ");
        System.out.println("---본인 확인을 위해 아이디를 입력해주세요 : ");
        String email = inputEmail();
        payoutputView.printMessage("\n---결제중...---");
        boolean result = PaymentController.payMoney(email);

        if(result) {
            payoutputView.printMessage("💰💰결제가 완료되었습니다.");
            return true;
        } else {
            payoutputView.printMessage("🤣🤣🤣 결제 처리 중 문제가 발생했습니다!!!");
            return false;
        }
    }

    private int inputInt() {
        while (true) {
            try {
                int num = sc.nextInt();
                return num;
            } catch (InputMismatchException e) {
                System.out.print("숫자만 입력해주세요 : ");
                sc.nextLine();
            }
        }
    }

    public void showPayment() {
        payoutputView.printMessage("---결제 내역 조회---");
        System.out.print("본인의 이메일을 입력해주세요 : ");
        String email = inputEmail();
        List<PaymentDTO> payList= paycontroller.findMyPayment(email);
        payoutputView.printPayments(payList);

    }

    private String inputEmail() {
        while (true) {
            String value = sc.next();
            try {
                if (paycontroller.checkEmail(value)) {
                    return value;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.print("존재하지 않는 이메일입니다. 다시 입력하세요: ");
        }
    }

    private String inputPwd() {
        while (true) {
            try {
                String password = String.format(sc.next());
                return password;
            } catch (NumberFormatException e) {
                System.out.print("올바른 비밀번호를 입력하세요 : ");
            }
        }
    }
}
