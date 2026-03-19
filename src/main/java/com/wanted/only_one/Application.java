package com.wanted.only_one;


import com.wanted.only_one.global.config.JDBCTemplate;
import com.wanted.only_one.member.controller.AuthController;
import com.wanted.only_one.member.service.AuthService;
import com.wanted.only_one.member.service.MemberService;
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

//            AuthService authService = new AuthService(con);
//            MemberService memberService= new MemberService(con);
//
//            AuthController authController = new AuthController(authService);
            /*
             * 강사 / 학생 회원가입
             * 강사 / 학생 로그인
             * 강사 / 학생 로그아웃
             * 학생 중복 로그인 시 블랙리스트
             * */
//            MemberController memberController = new MemberController(memberService); -> 마이페이지
            // 회원 정보 수정
            // 회원 정보 조회
            // 회원 정보 삭제


//            OutputView mainOutputView = new OutputView();
//
//            InputView mainInputView= new InputView(mainOutputView,authController);
//
//            mainInputView.displayStart();
            PaymentService service = new PaymentService(con);
            PaymentController controller = new PaymentController(service);
            PaymentOutputView outputView = new PaymentOutputView();
            PaymentInputView inputView = new PaymentInputView(controller, outputView);

            // Application 이 실랭되면 View 메서드를 호출
            inputView.pay();

        } catch (SQLException e) {
            System.err.println("🚨데이터베이스 연결 실패");
        }finally{
            JDBCTemplate.close();
        }
    }
}
