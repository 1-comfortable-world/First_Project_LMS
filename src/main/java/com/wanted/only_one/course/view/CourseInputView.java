package com.wanted.only_one.course.view;

import com.wanted.only_one.course.controller.CourseController;
import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.course.dto.LectureDTO;
import com.wanted.only_one.course.dto.SectionDTO;
import com.wanted.only_one.study.view.StudyInputView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CourseInputView {

    private final CourseController controller;
    private final CourseOutputView outputView;
    private final StudyInputView studyInputView;
    private final Scanner sc = new Scanner(System.in);

    public CourseInputView(CourseController controller, CourseOutputView outputView, StudyInputView studyInputView) {
        this.controller = controller;
        this.outputView = outputView;
        this.studyInputView = studyInputView;
    }

    // ── 학생용 ───────────────────────────────────────

    public void showAllCourses() throws SQLException {
        List<CourseDTO> list = controller.showAllCourses();
        outputView.printMessage("\n--- 강좌 목록 전체 조회 ---");
        outputView.printCourses(list);
    }

    public void courseSelection(long memberId) throws SQLException {
        while (true) {
            List<CourseDTO> courseList = controller.showAllCourses();

            // 학생은 수강할 목록을 먼저 보는 것이 흐름상 맞으므로 유지
            outputView.printCourses(courseList);

            System.out.println("=================================");
            System.out.println("          강좌 수강하기");
            System.out.println("=================================");
            System.out.println("1. 수강할 강좌 선택");
            System.out.println("2. 강좌평 조회하기");
            System.out.println("3. 이전으로 돌아가기");
            System.out.print("번호를 입력해주세요 : ");

            int menu = inputInt();

            if (menu == 1 && (courseList == null || courseList.isEmpty())) {
                outputView.printMessage("\n[안내] 현재 수강 가능한 강좌가 없습니다.");
                continue;
            }

            switch (menu) {
                case 1:
                    if (!controller.hasPaid(memberId)) {
                        outputView.printMessage("=================================");
                        outputView.printMessage("  결제 후 수강 신청이 가능합니다.");
                        outputView.printMessage("  결제 메뉴에서 먼저 결제해주세요.");
                        outputView.printMessage("=================================");
                        break;
                    }
                    long courseId = selectCourseByNumber(courseList);
                    if (courseId > 0) studyLecture(memberId, courseId);
                    break;
                case 2:
                    studyInputView.ShowReviewInCourse();
                    break;
                case 3:
                    return;
                default:
                    outputView.printError("");
            }
        }
    }

    private long selectCourseByNumber(List<CourseDTO> courseList) throws SQLException {
        outputView.printCourses(courseList);
        System.out.print("\n수강할 강좌 번호를 입력해주세요 (0: 뒤로가기) : ");
        int num = inputInt();

        if (num == 0) return -1;
        if (num < 1 || num > courseList.size()) {
            outputView.printMessage("올바른 번호를 입력해주세요.");
            return -1;
        }

        long courseId = courseList.get(num - 1).getCourseId();
        SectionDTO detail = controller.findJoin(courseId);
        if (detail == null) { outputView.printMessage("강좌 정보를 찾을 수 없습니다."); return -1; }
        outputView.printCourseDetail(detail);

        System.out.println("=================================");
        System.out.println("1. 수강하기");
        System.out.println("2. 뒤로가기");
        System.out.println("=================================");
        System.out.print("번호를 입력해주세요 : ");

        int choice = inputInt();
        return (choice == 1) ? courseId : -1;
    }

    private void studyLecture(long memberId, long courseId) throws SQLException {
        controller.enrollCourse(memberId, courseId);
        SectionDTO detail = controller.findJoin(courseId);
        outputView.printCourseDetail(detail);

        List<LectureDTO> lectures = detail.getLectures();
        if (lectures == null || lectures.isEmpty()) {
            outputView.printMessage("등록된 강의가 없습니다.");
            return;
        }

        System.out.println("=================================");
        System.out.println("수강할 강의를 선택해주세요.");
        System.out.println("0. 뒤로가기");
        System.out.println("=================================");
        System.out.print("강의 번호 : ");
        int num = inputInt();

        if (num == 0) return;
        if (num < 1 || num > lectures.size()) {
            outputView.printMessage("올바른 번호를 입력해주세요.");
            return;
        }

        long lectureId = lectures.get(num - 1).getLectureId();
        controller.completeLecture(memberId, lectureId, courseId);
        outputView.printSuccess("강의 수강 완료!");
    }

    // ── 강사용 ───────────────────────────────────────

    public void teacherCourseMenu(long memberId) throws SQLException {
        while (true) {
            List<CourseDTO> courseList = controller.T_showAllCourses(memberId);

            // 삭제된 부분: 여기서 outputView.printCourses(courseList)를 강제로 띄우던 걸 없앴습니다.

            System.out.println("=================================");
            System.out.println("           강사 메인페이지");
            System.out.println("=================================");
            System.out.println("1. 나의 강좌 전체 보기");
            System.out.println("2. 강좌/강의 등록하기");
            System.out.println("3. 강좌 수정하기");
            System.out.println("4. 강좌 삭제하기");
            System.out.println("5. 이전으로 돌아가기");
            System.out.print("번호를 입력해주세요 : ");

            int menu = inputInt();

            if ((menu == 1 || menu == 3 || menu == 4) && (courseList == null || courseList.isEmpty())) {
                outputView.printMessage("\n[안내] 등록된 강좌가 없습니다. 먼저 2번을 눌러 강좌를 등록해주세요.");
                continue;
            }

            switch (menu) {
                case 1: viewTeacherCourseDetail(memberId, courseList); break;
                case 2: createCourse(memberId); break;
                case 3: updateCourse(memberId, courseList); break;
                case 4: deleteCourse(memberId, courseList); break;
                case 5: return;
                default: outputView.printError("");
            }
        }
    }

    private void viewTeacherCourseDetail(long memberId, List<CourseDTO> courseList) throws SQLException {
        outputView.printCourses(courseList);
        System.out.print("\n상세 조회할 강좌 번호 (0: 뒤로가기) : ");
        int num = inputInt();
        if (num == 0) return;
        if (num < 1 || num > courseList.size()) { outputView.printMessage("올바른 번호를 입력해주세요."); return; }

        long courseId = courseList.get(num - 1).getCourseId();
        SectionDTO detail = controller.findJoin(courseId);
        if (detail == null) { outputView.printMessage("강좌 정보를 찾을 수 없습니다."); return; }
        outputView.printCourseDetail(detail);

        System.out.println("=================================");
        System.out.println("1. 수강생 보기");
        System.out.println("2. 강좌평 보기");
        System.out.println("3. 이전으로 돌아가기");
        System.out.println("=================================");
        System.out.print("번호를 입력해주세요 : ");

        int menu = inputInt();
        if (menu == 1) showStudentList(courseId);
        else if (menu == 2) studyInputView.ShowReviewInCourse();
    }

    private void createCourse(long memberId) throws SQLException {
        outputView.printMessage("\n--- 강좌 등록 ---");
        System.out.print("강좌 제목 : ");
        String title = sc.nextLine().trim();
        if (title.isEmpty()) { outputView.printFail("강좌 제목을 입력해주세요."); return; }

        long courseId = controller.addCourse(title, memberId);
        if (courseId == -1) { outputView.printFail("강좌 등록에 실패했습니다."); return; }
        outputView.printSuccess("강좌 등록 완료!");

        outputView.printMessage("\n--- 강의 등록 ('0' 입력 시 종료) ---");
        while (true) {
            System.out.print("강의 제목 : ");
            String lTitle = sc.nextLine().trim();

            if (lTitle.equals("0")) {
                outputView.printMessage("강의 등록을 완료했습니다.");
                break;
            }
            if (lTitle.isEmpty()) {
                outputView.printFail("강의 제목을 입력해주세요. (종료하려면 '0' 입력)");
                continue;
            }

            if (controller.addLecture(courseId, lTitle))
                outputView.printSuccess("강의 '" + lTitle + "' 등록 완료!");
            else outputView.printFail("강의 등록 실패.");
        }
    }

    private void updateCourse(long memberId, List<CourseDTO> courseList) throws SQLException {
        outputView.printCourses(courseList);
        System.out.print("\n수정할 강좌 번호 (0: 뒤로가기) : ");
        int num = inputInt();
        if (num == 0) return;
        if (num < 1 || num > courseList.size()) { outputView.printMessage("올바른 번호를 입력해주세요."); return; }

        long courseId = courseList.get(num - 1).getCourseId();
        System.out.print("새 강좌 제목 : ");
        String newTitle = sc.nextLine().trim();

        if (controller.updateCourse(courseId, newTitle, memberId)) {
            outputView.printSuccess("강좌 수정 완료!");
        } else {
            outputView.printFail("강좌 수정 실패");
        }
    }

    private void deleteCourse(long memberId, List<CourseDTO> courseList) throws SQLException {
        outputView.printCourses(courseList);
        System.out.print("\n삭제할 강좌 번호 (0: 뒤로가기) : ");
        int num = inputInt();
        if (num == 0) return;
        if (num < 1 || num > courseList.size()) { outputView.printMessage("올바른 번호를 입력해주세요."); return; }

        long courseId = courseList.get(num - 1).getCourseId();
        System.out.print("정말 삭제하시겠습니까? (Y/N) : ");
        if (!sc.nextLine().trim().equalsIgnoreCase("Y")) { outputView.printMessage("삭제를 취소했습니다."); return; }

        if (controller.deleteCourse(courseId)) {
            outputView.printSuccess("강좌 삭제 완료!");
        } else {
            outputView.printFail("강좌 삭제 실패.");
        }
    }

    private void showStudentList(long courseId) {
        outputView.printMessage("\n--- 현재 수강생 목록 ---");
        try {
            List<com.wanted.only_one.member.dto.MemberDTO> students = controller.getEnrolledStudents(courseId);
            if (students == null || students.isEmpty()) {
                outputView.printMessage("현재 수강 중인 학생이 없습니다.");
            } else {
                System.out.println("-------------------------------------------");
                System.out.printf("%-15s | %-25s\n", "이름", "이메일");
                System.out.println("-------------------------------------------");
                for (var student : students) {
                    System.out.printf("%-15s | %-25s\n", student.getName(), student.getEmail());
                }
                System.out.println("-------------------------------------------");
            }
        } catch (SQLException e) {
            outputView.printError("데이터 조회 중 오류 발생");
        }
    }

    private int inputInt() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("숫자만 입력해주세요 : "); }
        }
    }
}