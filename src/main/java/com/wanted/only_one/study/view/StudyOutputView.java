package com.wanted.only_one.study.view;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.dto.FavDTO;

import java.util.List;

public class StudyOutputView {
    public void printMessage(String s) {
        {
            System.out.println(s);
        }
    }

    public void printError(String s) {
            System.out.println("🚨"+s);
    }

    public void printSuccess(String s) {
        System.out.println("✅"+s);
    }

    public void printCourses(List<CourseDTO> courseList) {

        if(courseList == null || courseList.isEmpty()){
            System.out.println("조회된 강좌가 없습니다. ");
            return;
        }

        System.out.println("====강좌 전체 조회 목록 결과====");
        for (CourseDTO courseDTO : courseList){
            System.out.println(courseDTO);
        }
    }

    public void printCompletedCourses(List<CourseDTO> completedCourseList) {

        if(completedCourseList == null || completedCourseList.isEmpty()){
            System.out.println("수강 완료된 강좌가 없습니다. ");
            System.out.println("최소 하나의 강좌를 수강 완료한 후 이용해주세요.");
            return;
        }

        System.out.println("  ====수강 완료한 강좌 목록 전체 조회====");
        for (CourseDTO courseDTO : completedCourseList){
            System.out.println(courseDTO);
        }
    }


    public void printFavCourses(List<FavDTO> favList) {

        if(favList == null || favList.isEmpty()){
            System.out.println("조회된 강좌가 없습니다");
            return;
        }

        System.out.println("====강좌 선택 목록 조회 결과====");
        for (FavDTO favDTO : favList){
            System.out.println( "회원명 : "+favDTO.getMember_name() +"/ 강좌명 : "+favDTO.getCourse_title() );
        }
    }
}
