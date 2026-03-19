package com.wanted.only_one.course.view;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.course.dto.LectureDTO;
import com.wanted.only_one.course.dto.SectionDTO;

import java.util.List;

public class CourseOutputView {

    // 강좌 목록 출력
    public void printCourses(List<CourseDTO> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("등록된 강좌가 없습니다.");
            return;
        }
        System.out.println("=================================");
        System.out.printf("  %-5s  %-25s%n", "ID", "강좌명");
        System.out.println("---------------------------------");
        for (CourseDTO c : list)
            System.out.printf("  %-5d  %-25s%n", c.getCourseId(), c.getTitle());
        System.out.println("=================================");
    }

    // 강좌 + 강의 목록 통합 출력 (SectionDTO)
    public void printCourseDetail(SectionDTO section) {
        if (section == null) {
            System.out.println("강좌 정보를 찾을 수 없습니다.");
            return;
        }
        System.out.println("=================================");
        System.out.println("  강좌명 : " + section.getCourseTitle());
        System.out.println("  강좌ID : " + section.getCourseId());
        System.out.println("---------------------------------");
        System.out.println("  [강의 목록]");
        if (section.getLectures() == null || section.getLectures().isEmpty()) {
            System.out.println("    등록된 강의가 없습니다.");
        } else {
            for (LectureDTO l : section.getLectures())
                System.out.printf("    %-5d  %s%n", l.getLectureId(), l.getTitle());
        }
        System.out.println("=================================");
    }

    // 강사 강좌 전체 + 강의 목록 출력
    public void printAllCourseDetails(List<SectionDTO> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("등록한 강좌가 없습니다.");
            return;
        }
        for (SectionDTO section : list) printCourseDetail(section);
    }

    // 강의 목록만 출력
    public void printLectures(List<LectureDTO> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("강의가 없습니다.");
            return;
        }
        System.out.println("=================================");
        System.out.printf("  %-5s  %-25s%n", "ID", "강의명");
        System.out.println("---------------------------------");
        for (LectureDTO l : list)
            System.out.printf("  %-5d  %-25s%n", l.getLectureId(), l.getTitle());
        System.out.println("=================================");
    }

    public void printMessage(String msg) { System.out.println(msg); }
    public void printError(String msg)   { System.out.println("[오류] 올바른 번호를 입력해주세요. " + msg); }
    public void printSuccess(String msg) { System.out.println("[성공] " + msg); }
    public void printFail(String msg)    { System.out.println("[실패] " + msg); }
}
