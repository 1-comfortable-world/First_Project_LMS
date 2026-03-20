package com.wanted.only_one.study.view;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.member.dto.MemberDTO;
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

    private double inputDouble(double min, double max) {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine());
                if (value < min || value > max) {
                    System.out.printf("0~5.0 사이의 숫자를 입력해주세요 : ");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.print("숫자만 입력해주세요 : ");
            }
        }
    }

    private String inputNoBlank() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.equals("0")) {
                return null;
            }
            if (!input.isEmpty()) {
                return input;
            }
            System.out.print("⚠️ 빈 값은 입력할 수 없습니다. 다시 입력하거나 취소하려면 0을 누르세요 : ");

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

        // 등록된 강좌가 없는 경우 -> 아마 없을 거임
        if (courseList == null || courseList.isEmpty()) {
            studyOutputView.printError("현재 등록된 강좌가 없습니다.");
            return;
        }

        studyOutputView.printCourses(courseList);

        while (true) {
            System.out.println(" ");
            System.out.println("1. 수강 예정 목록에 넣을 강좌 고르기");
            System.out.println("2. 강좌 선택 그만하기");
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
    }

    public void showFavList() {
        List<FavDTO> favList = studyController.showFavList();

        if (favList == null || favList.isEmpty()) {
            studyOutputView.printError("선택 목록에 담긴 강좌가 없습니다.");
            return;
        }

        studyOutputView.printFavCourses(favList);

        while (true) {
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
    }

    public void chooseCourseList() {
        System.out.println("---수강 예정 목록 강좌 선택---");
        System.out.print("선택 목록에 넣고자 하는 강좌의 제목을 입력하세요 : ");
        String description = inputNoBlank();

        if (description == null) {
            studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
            return;
        }

        Boolean result = studyController.addFavList(description);

        if (result != null && result == true) {
            studyOutputView.printSuccess("수강 예정 목록에 \"" + description + "\" 강좌가 추가되었습니다!");
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
            System.out.println("              강좌평");
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
                    break;
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

        while (true) {
            System.out.println(" ");
            System.out.println("1. 강좌평 작성하기");
            System.out.println("2. 강좌평 작성 그만두기");
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
    }

    public void WriteReview() {

        // 학생만 리뷰 작성 가능 -> 학생이 아닌 경우 예외처리
//        if (!MemberDTO.getRole().equals("STUDENT")) {
//            studyOutputView.printError("학생만 이용할 수 있는 기능입니다.");
//            return;
//        }

        System.out.println("=================================");
        System.out.println("         강좌평 작성하기");
        System.out.println("=================================");

        System.out.println("<     한 번 작성된 강좌평은 수정하실 수 없습니다     >");
        System.out.println("< 함께 공부하는 다른 사용자를 위해 신중히 작성해주세요 >");
        System.out.println(" ");
        String description;
        while (true) {
            System.out.print("강좌평을 작성할 강좌의 제목을 입력하세요 : ");
            description = inputNoBlank();
            if (description == null) {
                studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                return;
            }
            if (studyController.checkCourseExists(description)) {
                break;
            }
            studyOutputView.printError("해당 강좌를 찾을 수 없습니다. 강좌명을 다시 확인해주세요.");
            System.out.println("이전으로 돌아가려면 0을 누르세요");
            // while 다시 돌아서 재입력
        }


        System.out.print("선택한 강좌에 작성할 강좌평을 입력하세요 : ");
        String content =  inputNoBlank();
        if (content == null) {
            studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
            return;
        }
        System.out.print("선택한 강좌에 별점을 매겨주세요 : ");
        double rating = inputDouble(0.0,5.0);

        Boolean result = studyController.WriteReview(description,content,rating);

        if (result == null) {
            studyOutputView.printError("❗이미 해당 강좌에 강좌평을 작성하셨습니다."); // ✅ 친절한 안내
        } else if (result) {
            studyOutputView.printSuccess("✅강좌평이 성공적으로 생성되었습니다.");
        } else {
            studyOutputView.printError("🚨강좌평 작성 중 문제 발생");
        }
    }


    // 학생이 자기가 쓴 강좌평 조회 -> 강좌평 작성하기에서 showMyReview
    // 강좌명과 쓴 강좌평 조회
    public void showMyReview() {
        List<ReviewDTO> MyReviewList = studyController.showMyReviewList();

        if (MyReviewList == null || MyReviewList.isEmpty()) {
            studyOutputView.printError("작성하신 강좌평이 없습니다.");
            return;
        }

        studyOutputView.printMyReview(MyReviewList);

        while (true) {
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
    }

    // 특정 강좌에 있는 강좌평 조회
    public void ShowReviewInCourse() {
        while (true) {
            System.out.println("=========================================");
            System.out.println(" 강좌평을 조회하고자 하는 강좌명을 입력하세요 ");
            System.out.println("=========================================");
            System.out.println("입력 : ");
            String description = inputNoBlank();

            if (description == null) {
                studyOutputView.printMessage("== 이전 화면으로 돌아갑니다. ==");
                return;
            }

            List<ReviewDTO> reviewInCourse = studyController.ShowReviewInCourse(description);

            if (reviewInCourse == null || reviewInCourse.isEmpty()) {
                studyOutputView.printError("\"" + description + "\"에 관한 검색결과가 없습니다. 다시 입력해주십시오.");
                continue;  // 다시 입력받기
            }

            studyOutputView.printReviewInCourse(reviewInCourse);

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
    }

    // 강사가 자기 강좌에 쓰여진 강좌평 조회
    public void ShowReviewForTeacher(){
        List<ReviewDTO> ReviewForTeacher = studyController.ShowReviewForTeacher();

        if (ReviewForTeacher == null || ReviewForTeacher.isEmpty()) {
            studyOutputView.printError("개설하신 강좌가 없거나 작성된 강좌평이 없습니다.");
            return;  // 이전 화면으로 바로 복귀
        }

        studyOutputView.printReviewForTeacher(ReviewForTeacher);

        while (true) {
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
    }


    /* comment
        사용자가 로그인 후 나타나는 선택 목록에서 마이페이지 -> 나의 수강 리스트
     */
    public void MyStudying(){
        while (true) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("      나의 강좌 수강 상태 보기");
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
                if (favList == null || favList.isEmpty()) {
                    studyOutputView.printError("수강 예정인 강좌가 없습니다.");
                    return;
                }
                studyOutputView.printFavCourses(favList);
                break;
            case 2:
                System.out.println("====수강 중인 강좌 목록====");
                List<CourseDTO> studyingList = studyController.showMyStudyingList(menu);
                if (studyingList == null || studyingList.isEmpty()) {
                    studyOutputView.printError("수강 중인 강좌가 없습니다.");
                    return;
                }
                studyOutputView.printCourses(studyingList);
                break;
            case 3:
                System.out.println("====수강 완료한 강좌 목록====");
                List<CourseDTO> completedList = studyController.showMyStudyingList(menu);
                if (completedList == null || completedList.isEmpty()) {
                    studyOutputView.printError("수강 완료한 강좌가 없습니다.");
                    return;
                }
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
