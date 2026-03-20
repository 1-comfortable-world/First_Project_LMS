package com.wanted.only_one.course.service;

import com.wanted.only_one.course.dao.CourseDAO;
import com.wanted.only_one.course.dao.CourseSectionDAO;
import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.course.dto.SectionDTO;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private CourseDAO courseDAO               = new CourseDAO();
    private CourseSectionDAO courseSectionDAO = new CourseSectionDAO();

    // 학생 - 전체 강좌 조회
    public List<CourseDTO> getAllCourses() throws SQLException {
        return courseDAO.findAll();
    }

    // 강사 - 본인 강좌 조회
    public List<CourseDTO> getT_AllCourses(long memberId) throws SQLException {
        return courseDAO.findByMemberId(memberId);
    }

    // 강좌 + 강의 목록 JOIN 조회
    public SectionDTO findCourseWithSections(long courseId) throws SQLException {
        return courseSectionDAO.findCourseWithLectures(courseId);
    }

    // 강사 본인 강좌 전체 + 강의 JOIN 조회
    public List<SectionDTO> findAllWithSections(long memberId) throws SQLException {
        return courseSectionDAO.findAllWithLecturesByMemberId(memberId);
    }

    // 강좌 등록
    public long addCourse(String title, long memberId) throws SQLException {
        return courseDAO.insert(new CourseDTO(title, memberId));
    }

    // 강좌 수정
    public boolean updateCourse(long courseId, String newTitle, long memberId) throws SQLException {
        return courseDAO.update(new CourseDTO(courseId, newTitle, memberId));
    }

    // 강좌 삭제
    public boolean deleteCourse(long courseId) throws SQLException {
        return courseDAO.delete(courseId);
    }

    // 강좌 검색 (기본)
    public List<CourseDTO> searchCourse(String keyword) throws SQLException {
        return courseDAO.searchByTitle(keyword);
    }

    // 강좌 검색 + 별점 (별점 높은 순)
    public List<CourseDTO> searchCourseWithRating(String keyword) throws SQLException {
        return courseDAO.searchByTitleWithRating(keyword);
    }
}
