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

    public List<CourseDTO> showMyStudyingList(int menu) {
        try {
            return studyingDAO.showMyStudyingList(menu);
        } catch (SQLException e) {
            throw new RuntimeException("수강 강좌 조회 중 에러 발생 🚨"+e);
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
            throw new RuntimeException("강좌 상태 업데이트 중 에러 발생 🚨" + e);
        }
    }
}
