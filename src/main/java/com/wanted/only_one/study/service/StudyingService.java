package com.wanted.only_one.study.service;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.dao.LectureHistoryDAO;
import com.wanted.only_one.study.dao.StudyingDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyingService {

    private final Connection connection;
    private final StudyingDAO studyingDAO;
    private final LectureHistoryDAO lectureHistoryDAO;

    System.out.println("시작");

    public StudyingService(Connection connection) {
        this.studyingDAO = new StudyingDAO(connection);
        this.lectureHistoryDAO = new LectureHistoryDAO(connection);
        this.connection = connection;
    }

    public List<CourseDTO> showMyStudyingList(long memberId, int menu) {
        try {
            return studyingDAO.showMyStudyingList(memberId, menu);
        } catch (SQLException e) {
            throw new RuntimeException("수강 강좌 조회 중 에러 발생 🚨");
        }
    }

    public void updateCourseStatus(long memberId, long courseId) {
        try {
            boolean allCompleted = lectureHistoryDAO.AllLecturesCompleted(memberId, courseId);
            if (allCompleted) {
                studyingDAO.updateCourseStatus(memberId, courseId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("강좌 상태 업데이트 중 에러 발생 🚨");
        }
    }

    public void completeLecture(long memberId, long lectureId, long courseId) {
        try {
            lectureHistoryDAO.insertLectureHistory(memberId, lectureId);
            updateCourseStatus(memberId, courseId);
        } catch (SQLException e) {
            throw new RuntimeException("강의 수강 처리 중 에러 발생 🚨");
        }
    }

    public void enrollCourse(long memberId, long courseId) {
        try {
            if (!studyingDAO.existsStudyingCourse(memberId, courseId)) {
                studyingDAO.insertStudyingCourse(memberId, courseId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("수강 신청 중 에러 발생 🚨");
        }
    }

    public Map<Long, String> getLectureStatusMap(long memberId, long courseId) {
        try {
            return lectureHistoryDAO.getLectureStatusMap(memberId, courseId);
        } catch (SQLException e) {
            throw new RuntimeException("강의 상태 조회 중 에러 발생 🚨");
        }
    }

    public boolean isAllLecturesComplete(long memberId, long courseId) {
        try {
            return lectureHistoryDAO.isAllLecturesComplete(memberId, courseId);
        } catch (SQLException e) {
            throw new RuntimeException("강의 완료 여부 확인 중 에러 발생 🚨");
        }
    }
}
