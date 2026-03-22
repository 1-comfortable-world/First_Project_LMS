package com.wanted.only_one.study.service;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.dao.LectureHistoryDAO;
import com.wanted.only_one.study.dao.StudyingDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudyingService {

    private final Connection connection;
    private final StudyingDAO studyingDAO;
    private final LectureHistoryDAO lectureHistoryDAO;

    public StudyingService(Connection connection) {
        this.studyingDAO = new StudyingDAO(connection);
        this.lectureHistoryDAO = new LectureHistoryDAO(connection);
        this.connection = connection;
    }

    public List<CourseDTO> showMyStudyingList(long memberId,int menu) {
        try {
            return studyingDAO.showMyStudyingList(memberId,menu);
        } catch (SQLException e) {
            throw new RuntimeException("수강 강좌 조회 중 에러 발생 🚨");
        }
    }

    // 강의가 다 수강완료 되면 강좌가 수강 완료
    public void updateCourseStatus(long memberId, long courseId) {
        try {
            boolean allCompleted = lectureHistoryDAO.AllLecturesCompleted(memberId, courseId);
            if (allCompleted) {
                studyingDAO.updateCourseStatus(memberId, courseId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("강좌 상태 업데이트 중 에러 발생 🚨" );
        }
    }

    public void completeLecture(long memberId, long lectureId, long courseId) {
        try {
            lectureHistoryDAO.insertLectureHistory(memberId, lectureId); // 강의 수강 기록 저장
            updateCourseStatus(memberId, courseId); // 전체 완료 여부 체크
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


}
