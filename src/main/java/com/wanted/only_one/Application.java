package com.wanted.only_one;

import com.wanted.only_one.global.config.JDBCTemplate;
import com.wanted.only_one.member.controller.AuthController;
import com.wanted.only_one.member.controller.MemberController;
import com.wanted.only_one.member.service.AuthService;
import com.wanted.only_one.member.service.MemberService;
import com.wanted.only_one.member.view.*;
import com.wanted.only_one.payments.PaymentController;
import com.wanted.only_one.payments.PaymentInputView;
import com.wanted.only_one.payments.PaymentOutputView;
import com.wanted.only_one.payments.PaymentService;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {

        try (Connection con = JDBCTemplate.getConnection()) {
            System.out.println("✅데이터베이스 연결 성공");
            JDBCTemplate.printConnectionStatus();

            AuthService authService = new AuthService(con);
            MemberService memberService = new MemberService(con);


            AuthController authController = new AuthController(authService);
            MemberController memberController = new MemberController(memberService);

            MemberOutputView memberoutputView = new MemberOutputView();
            PaymentOutputView paymentOutputView = new PaymentOutputView();
            PaymentService paymentService = new PaymentService(con);
            PaymentController paymentController = new PaymentController(paymentService);
            PaymentInputView payInputView = new PaymentInputView(paymentController, paymentOutputView);


            MemberInputView InputView= new MemberInputView(authController, memberoutputView, payInputView);

            InputView.displayMainMenu();

        } catch (SQLException e) {
            System.err.println("🚨데이터베이스 연결 실패");
        }finally{
            JDBCTemplate.close();
        }
    }
}
