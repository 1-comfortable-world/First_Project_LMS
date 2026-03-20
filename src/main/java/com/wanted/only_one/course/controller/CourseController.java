package com.wanted.only_one.course.controller;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.course.dto.LectureDTO;
import com.wanted.only_one.course.dto.SectionDTO;
import com.wanted.only_one.course.service.CourseService;
import com.wanted.only_one.course.service.LectureService;

import java.sql.SQLException;
import java.util.List;

public class CourseController {

    private CourseService  courseService  = new CourseService();
    private LectureService lectureService = new LectureService();

    // ── 학생용 ───────────────────────────────────────

    public List<CourseDTO> showAllCourses() throws SQLException {
        return courseService.getAllCourses();
    }

    // 강좌 검색 (기본)
    public List<CourseDTO> searchCourse(String keyword) throws SQLException {
        return courseService.searchCourse(keyword);
    }

    // 강좌 검색 + 별점 (별점 높은 순)
    public List<CourseDTO> searchCourseWithRating(String keyword) throws SQLException {
        return courseService.searchCourseWithRating(keyword);
    }

    public SectionDTO findJoin(long courseId) throws SQLException {
        return courseService.findCourseWithSections(courseId);
    }

    // 강의 수강완료 + 강좌 상태 자동 변경 → study 팀 연동 대기 중
    public void updateCourseStatus(long memberId, long courseId) throws SQLException {
        // studyController.updateCourseStatus(memberId, courseId);
        System.out.println("수강완료 처리 예정 (study 팀 연동 대기 중)");
    }

    // ── 강사용 ───────────────────────────────────────

    public List<CourseDTO> T_showAllCourses(long memberId) throws SQLException {
        return courseService.getT_AllCourses(memberId);
    }

    public List<SectionDTO> T_findAllWithSections(long memberId) throws SQLException {
        return courseService.findAllWithSections(memberId);
    }

    public long addCourse(String title, long memberId) throws SQLException {
        return courseService.addCourse(title, memberId);
    }

    public boolean addLecture(long courseId, String title) throws SQLException {
        return lectureService.addLecture(courseId, title);
    }

    public List<LectureDTO> getLectures(long courseId) throws SQLException {
        return lectureService.getLectures(courseId);
    }

    public boolean updateCourse(long courseId, String newTitle, long memberId) throws SQLException {
        return courseService.updateCourse(courseId, newTitle, memberId);
    }

    public boolean deleteCourse(long courseId) throws SQLException {
        return courseService.deleteCourse(courseId);
    }
}
