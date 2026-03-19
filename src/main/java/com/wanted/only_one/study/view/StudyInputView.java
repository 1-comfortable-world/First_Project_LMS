package com.wanted.only_one.study.view;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.controller.StudyController;
import com.wanted.only_one.study.dto.FavDTO;
import com.wanted.only_one.study.dto.ReviewDTO;
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

    public void showCourseList() {
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

    public void showFavList() {
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

    public void chooseCourseList() {
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
            System.out.println("2. 내가 쓴 강좌평 보기");
            System.out.println("3. 전으로 돌아가기");
            System.out.print("번호를 입력해주세요 : ");

            int menu = inputInt();

            switch (menu) {
                case 1:
                    showReviewableList();
                    break;
                case 2:
                    showMyReview();
                    return;
                case 3:
                    studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                    return;
                default:
                    studyOutputView.printError("다시 선택해주세요.");
            }
        }
    }

    public void showReviewableList() {

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

    public void WriteReview() {

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


    // 학생이 자기가 쓴 강좌평 조회 -> 강좌평 작성하기에서 showMyReview
    // 강좌명과 쓴 강좌평 조회
    public void showMyReview() {
        List<ReviewDTO> MyReviewList = studyController.showMyReviewList();

        studyOutputView.printMyReview(MyReviewList);

        System.out.println(" ");
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

    // 강좌에 있는
    public void ShowReview(){

    }

    // 강사가 자기 강좌에 쓰여진 강좌평 조회


    /* comment
        사용자가 로그인 후 나타나는 선택 목록에서 마이페이지 -> 나의 수강 리스트
     */
    public void MyStudying(){
        while (true) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("         나의 강좌 수강 상태 보기");
            System.out.println("=================================");
            System.out.println("1. 수강 예정인 강좌 보기");
            System.out.println("2. 수강 중인 강좌 보기");
            System.out.println("3. 수강 완료한 강좌 보기");
            System.out.println("4. 전으로 돌아가기");
            System.out.print("번호를 입력해주세요 : ");

            int menu = inputInt();

            switch (menu) {
                case 1:
                    showMyStudyingList(menu);
                    break;
                case 2:
                    showMyStudyingList(menu);
                    break;
                case 3:
                    showMyStudyingList(menu);
                    break;
                case 4:
                    studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                    return;
                default:
                    studyOutputView.printError("다시 선택해주세요.");
            }
        }
    }
    public void showMyStudyingList(int menu) {
        switch (menu) {
            case 1:
                System.out.println("====수강 예정인 강좌 목록====");
                List<FavDTO> favList = studyController.showFavList();
                studyOutputView.printFavCourses(favList);
                break;
            case 2:
                System.out.println("====수강 중인 강좌 목록====");
                List<CourseDTO> studyingList = studyController.showMyStudyingList(menu);
                studyOutputView.printCourses(studyingList);
                break;
            case 3:
                System.out.println("====수강 완료한 강좌 목록====");
                List<CourseDTO> completedList = studyController.showMyStudyingList(menu);
                studyOutputView.printCourses(completedList);
                break;
        }
    }

     /* comment
         강좌 속 강의가 전부 수강완료가 되면 강좌가 수강완료
         -> 사용자는 강의를 수강하기만 하니까 status의 변화는 뷰로 지정할 게 아님
         -> 내부 로직으로만 처리
     */

}
