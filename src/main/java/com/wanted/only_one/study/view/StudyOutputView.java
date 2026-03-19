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
            System.out.println("조회된 강좌가 없습니다");
            return;
        }

        System.out.println("결과)");
        for (CourseDTO courseDTO : courseList){
            System.out.println("course = " + courseDTO);
        }
    }


    public void printFavCourses(List<FavDTO> favList) {

        if(favList == null || favList.isEmpty()){
            System.out.println("조회된 강좌가 없습니다");
            return;
        }

        System.out.println("결과)");
        for (FavDTO favDTO : favList){
            System.out.println( "member_id:"+favDTO.getMember_id() +"Course_id:"+favDTO.getCourse_id() );
        }
    }
}
