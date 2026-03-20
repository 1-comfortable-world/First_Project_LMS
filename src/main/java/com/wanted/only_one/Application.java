package com.wanted.only_one;

import com.wanted.only_one.course.controller.CourseController;
import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.course.view.CourseOutputView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {

        CourseController controller = new CourseController();
        CourseOutputView outputView = new CourseOutputView();

        // 강좌 검색 테스트
        System.out.print("검색어 입력 : ");
        Scanner sc = new Scanner(System.in);
        String keyword = sc.nextLine();

        List<CourseDTO> result = controller.searchCourse(keyword);
        outputView.printCourses(result);
    }
}

