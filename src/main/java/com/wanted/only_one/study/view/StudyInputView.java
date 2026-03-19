package com.wanted.only_one.study.view;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.controller.StudyController;
import com.wanted.only_one.study.dto.FavDTO;
import com.wanted.only_one.study.dto.StudyingDTO;

import java.util.List;
import java.util.Scanner;

public class StudyInputView {

    private final StudyController studyController;
    private final StudyOutputView studyOutputView;
    private final Scanner sc = new Scanner(System.in);

    public StudyInputView(StudyOutputView studyOutputView,StudyController studyController){
        this.studyController=studyController;
        this.studyOutputView=studyOutputView;
    }

    private int inputInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("숫자만 입력해주세요 : ");
            }
        }
    }

    /*comment
    *  사용자가 로그인 후 나타나는 선택 목록에서 선택 목록을 누른 경우
    * */
    public void ChooseFav(){
        while (true) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("         선택 목록 고르기");
            System.out.println("=================================");
            System.out.println("1. 전체 강좌 목록 보기");
            System.out.println("2. 선택 목록 강좌 조회하기");
            System.out.println("3. 전으로 돌아가기");
            System.out.print("번호를 입력해주세요 : ");

            int menu = inputInt();

            switch (menu) {
                case 1:
                    showCourseList();
                    break;
                case 2:
                    showFavList();
                    break;
                case 3:
                    studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                    return;
                default:
                    studyOutputView.printError("다시 선택해주세요.");
            }
        }
    }

    private void showCourseList() {
        List<CourseDTO> courseList = studyController.showCourseList();

        studyOutputView.printCourses(courseList);

        System.out.println(" ");
        System.out.println("1. 강좌 선택 목록에 넣을 강좌 고르기");
        System.out.println("2. 강좌 선택 그만하기");
        System.out.println("<2를 입력하시면 이전 화면으로 돌아갑니다> ");
        System.out.print("번호를 입력해주세요 : ");
        int menu = inputInt();
        switch (menu) {
            case 1:
                chooseCourseList();
                break;
            case 2:
                studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                return;
            default:
                studyOutputView.printError("다시 선택해주세요.");
        }

    }

    private void showFavList() {
        List<FavDTO> favList = studyController.showFavList();

        studyOutputView.printFavCourses(favList);

        System.out.println(" ");
        System.out.println("0. 나가기 ");
        System.out.print("이전 화면으로 나가시려면 0번을 누르세요 : ");
        int menu = inputInt();
        switch (menu) {
            case 0:
                studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                return;
            default:
                studyOutputView.printError("다시 선택해주세요.");
        }

    }

    private void chooseCourseList() {
        System.out.println("---강좌 선택 목록 강좌 선택---");
        System.out.print("선택 목록에 넣고자 하는 과목의 제목을 입력하세요 : ");
        String description = sc.nextLine();

        Boolean result = studyController.addFavList(description);

        if (result != null && result == true) {
            studyOutputView.printSuccess("강좌 선택 목록에 강좌 추가 성공! " + result);
        } else {
            studyOutputView.printError("강좌 추가 실패");
        }
    }



    /* comment
        사용자가 로그인 후 나타나는 선택 목록에서 강좌평 작성하기를 누른 경우
     */
    public void Review(){
        while (true) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("         강좌평 작성하기");
            System.out.println("=================================");
            System.out.println("1. 강좌평 작성 가능한 목록 보기");
            System.out.println("2. 전으로 돌아가기");
            System.out.print("번호를 입력해주세요 : ");

            int menu = inputInt();

            switch (menu) {
                case 1:
                    showReviewableList();
                    break;
                case 2:
                    studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                    return;
                default:
                    studyOutputView.printError("다시 선택해주세요.");
            }
        }
    }

    private void showReviewableList() {

        List<CourseDTO> completedCourseList = studyController.showcompletedCourseList();

        studyOutputView.printCompletedCourses(completedCourseList);

        if(completedCourseList == null || completedCourseList.isEmpty()){
            return;
        }

        System.out.println(" ");
        System.out.println("1. 강좌평 작성하기 ");
        System.out.println("2. 강좌평 작성 그만두기");
        System.out.println("<2를 입력하시면 이전 화면으로 돌아갑니다> ");
        System.out.print("번호를 입력해주세요 : ");
        int menu = inputInt();
        switch (menu) {
            case 1:
                WriteReview();
                break;
            case 2:
                studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                return;
            default:
                studyOutputView.printError("다시 선택해주세요.");
        }
    }

    private void WriteReview() {

        System.out.println("---강좌평 작성하기---");
        System.out.print("강좌평을 작성할 강좌의 제목을 입력하세요 : ");
        String description = sc.nextLine();
        System.out.print("선택한 강좌에 작성할 강좌평을 입력하세요 : ");
        String content = sc.nextLine();
        System.out.print("선택한 강좌에 별점을 매겨주세요 : ");
        Double rating = sc.nextDouble();

        Boolean result = studyController.WriteReview(description,content,rating);

        if (result) {
            studyOutputView.printSuccess("✅강좌평이 성공적으로 생성되었습니다");
        } else {
            studyOutputView.printError("🚨강좌평 작성 중 문제 발생");
        }
    }


// 강좌평 조회는 ShowReview()


    /* comment
        사용자가 로그인 후 나타나는 선택 목록에서 마이페이지 -> 나의 수강 리스트 
     */

}
