package com.wanted.only_one;

import com.wanted.only_one.course.controller.CourseController;
import com.wanted.only_one.course.view.CourseInputView;
import com.wanted.only_one.course.view.CourseOutputView;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws SQLException {

        CourseController controller = new CourseController();
        CourseOutputView outputView = new CourseOutputView();
        CourseInputView  inputView  = new CourseInputView(controller, outputView);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=================================");
            System.out.println("         LMS 테스트 메뉴");
            System.out.println("=================================");
            System.out.println("1. 학생 메뉴");
            System.out.println("2. 강사 메뉴");
            System.out.println("0. 종료");
            System.out.println("=================================");
            System.out.print("번호를 입력해주세요 : ");

            int menu = inputInt(sc);
            switch (menu) {
                case 1:
                    studentMenu(inputView, controller, outputView, sc);
                    break;
                case 2:
                    teacherMenu(inputView, sc);
                    break;
                case 0:
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("[오류] 올바른 번호를 입력해주세요.");
            }
        }
    }

    // ── 학생 메뉴 ─────────────────────────────────────
    private static void studentMenu(CourseInputView inputView, CourseController controller,
                                    CourseOutputView outputView, Scanner sc) throws SQLException {
        // 테스트용 학생 memberId (더미 데이터 기준 학생 = 6번)
        long memberId = 6L;

        while (true) {
            System.out.println("=================================");
            System.out.println("          학생 메뉴");
            System.out.println("=================================");
            System.out.println("1. 전체 강좌 목록 보기");
            System.out.println("2. 강좌 수강하기");
            System.out.println("3. 강좌 검색 (별점 순)");
            System.out.println("0. 뒤로가기");
            System.out.println("=================================");
            System.out.print("번호를 입력해주세요 : ");

            int menu = inputInt(sc);
            switch (menu) {
                case 1:
                    inputView.showAllCourses();
                    break;
                case 2:
                    inputView.courseSelection(memberId);
                    break;
                case 3:
                    System.out.print("검색어 입력 : ");
                    String keyword = sc.nextLine().trim();
                    outputView.printCoursesWithRating(controller.searchCourseWithRating(keyword));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("[오류] 올바른 번호를 입력해주세요.");
            }
        }
    }

    // ── 강사 메뉴 ─────────────────────────────────────
    private static void teacherMenu(CourseInputView inputView, Scanner sc) throws SQLException {
        // 테스트용 강사 memberId (더미 데이터 기준 강사 = 1번)
        long memberId = 1L;

        inputView.teacherCourseMenu(memberId);
    }

    // ── 공통 입력 ─────────────────────────────────────
    private static int inputInt(Scanner sc) {
        while (true) {
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("숫자만 입력해주세요 : "); }
        }
    }
}
