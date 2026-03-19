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

    // 전체 강좌 목록 (학생)
    public List<CourseDTO> showAllCourses() throws SQLException {
        return courseService.getAllCourses();
    }

    // 강좌 검색
    public List<CourseDTO> searchCourse(String keyword) throws SQLException {
        return courseService.searchCourse(keyword);
    }

    // 강좌 단건 + 강의 목록 JOIN 조회 (강좌 선택 시)
    public SectionDTO findJoin(long courseId) throws SQLException {
        return courseService.findCourseWithSections(courseId);
    }

    // ── 강사용 ───────────────────────────────────────

    // 강사 본인 강좌 목록
    public List<CourseDTO> T_showAllCourses(long memberId) throws SQLException {
        return courseService.getT_AllCourses(memberId);
    }

    // 강사 본인 강좌 전체 + 강의 JOIN 조회
    public List<SectionDTO> T_findAllWithSections(long memberId) throws SQLException {
        return courseService.findAllWithSections(memberId);
    }

    // 강좌 등록
    public long addCourse(String title, long memberId) throws SQLException {
        return courseService.addCourse(title, memberId);
    }

    // 강의 등록
    public boolean addLecture(long courseId, String title) throws SQLException {
        return lectureService.addLecture(courseId, title);
    }

    // 강의 목록 조회
    public List<LectureDTO> getLectures(long courseId) throws SQLException {
        return lectureService.getLectures(courseId);
    }

    // 강좌 수정
    public boolean updateCourse(long courseId, String newTitle, long memberId) throws SQLException {
        return courseService.updateCourse(courseId, newTitle, memberId);
    }

    // 강좌 삭제
    public boolean deleteCourse(long courseId) throws SQLException {
        return courseService.deleteCourse(courseId);
    }
}
