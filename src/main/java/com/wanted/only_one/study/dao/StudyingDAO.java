package com.wanted.only_one.study.dao;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.global.utils.QueryUtil;
import com.wanted.only_one.study.dto.StudyingDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudyingDAO {


    private final Connection connection;

    public StudyingDAO(Connection connection) {
        this.connection = connection;
    }


    public List<CourseDTO> showcompletedCourseList() throws SQLException {
        // 동작시킬 쿼리문 준비
        String query = QueryUtil.getQuery("showcompletedCourseList");
        List<CourseDTO> completedCourseList = new ArrayList<>();

        // 쿼리문 동작
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, 1L); // TODO: 로그인 세션 연결 후 교체
            ResultSet rset = pstmt.executeQuery();

            while(rset.next()){
                CourseDTO completedCourse = new CourseDTO(
                        rset.getString("title")
                );
                completedCourseList.add(completedCourse);
            }
        }
        return completedCourseList;
    }


}
