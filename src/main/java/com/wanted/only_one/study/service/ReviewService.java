package com.wanted.only_one.study.service;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.dao.ReviewDAO;
import com.wanted.only_one.study.dao.StudyingDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReviewService {

    private final StudyingDAO studyingDAO;
    private final ReviewDAO reviewDAO;
    private final Connection connection;


    public ReviewService(Connection connection) {
        this.studyingDAO = new StudyingDAO(connection);
        this.reviewDAO = new ReviewDAO(connection);
        this.connection = connection;
    }



    public List<CourseDTO> showcompletedCourseList() {
        try {
            return studyingDAO.showcompletedCourseList();
        } catch (SQLException e) {
            throw new RuntimeException("수강 완료 강좌 조최 중 에러 발생 🚨"+e);
        }
    }

    public Boolean WriteReview(String description, String content, Double rating) {
        try {
            connection.setAutoCommit(false);

            // save(newfavcourse)를 통해 얻은 추가할 과목 목록에의 과목id를 저장
            Long writereview = reviewDAO.WriteReview(description,content,rating);

            if(writereview == null){
                throw new SQLException("🚨강좌 ID 생성에 실패했습니다 ");
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException("롤백 중 에러 발생"+ex);
            }
            return false;
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
