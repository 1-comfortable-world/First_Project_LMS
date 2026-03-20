package com.wanted.only_one;

import com.wanted.only_one.study.controller.StudyController;
import com.wanted.only_one.study.service.FavService;
import com.wanted.only_one.study.service.ReviewService;
import com.wanted.only_one.study.service.StudyingService;
import com.wanted.only_one.study.view.StudyInputView;
import com.wanted.only_one.study.view.StudyOutputView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) throws SQLException {
        // 1. DB 연결
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/only_one", "only", "only"
        );

        // 2. 객체 생성 (의존성 주입 순서 중요)
        FavService favService = new FavService(connection);
        ReviewService reviewService = new ReviewService(connection);
        StudyingService studyingService = new StudyingService(connection);
        StudyController studyController = new StudyController(favService,reviewService,studyingService);
        StudyOutputView studyOutputView = new StudyOutputView();
        StudyInputView studyInputView = new StudyInputView(studyOutputView, studyController);

        // 테스트할 기능 선택
        Scanner sc = new Scanner(System.in);
        System.out.println("1. 선택 목록 기능");
        System.out.println("2. 강좌평 기능");
        System.out.println("3. 나의 수강 상태 기능");
        System.out.print("번호를 입력해주세요 : ");

        int menu = Integer.parseInt(sc.nextLine());
        switch (menu) {
            case 1: studyInputView.ChooseFav(); break;
            case 2: studyInputView.Review(); break;
            case 3: studyInputView.MyStudying(); break;
            case 4: studyInputView.showMyReview(); break;
            case 5: studyInputView.ShowReviewForTeacher(); break;
            case 6: studyInputView.ShowReviewInCourse(); break;
        }
    }
}
