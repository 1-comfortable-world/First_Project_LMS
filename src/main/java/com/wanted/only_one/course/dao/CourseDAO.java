package com.wanted.only_one.course.dao;

import com.wanted.only_one.global.config.JDBCTemplate;
import com.wanted.only_one.course.dto.CourseDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection conn;

    public CourseDAO() {
        try {
            this.conn = JDBCTemplate.getConnection();
        } catch (SQLException e) {
            System.out.println("[CourseDAO] DB 연결 실패: " + e.getMessage());
        }
    }

    // course.findAll
    public List<CourseDTO> findAll() throws SQLException {
        String sql = "SELECT course_id, title, member_id FROM courses";
        List<CourseDTO> list = new ArrayList<>();
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    // course.findByMemberId
    public List<CourseDTO> findByMemberId(long memberId) throws SQLException {
        String sql = "SELECT course_id, title, member_id FROM courses WHERE member_id = ?";
        List<CourseDTO> list = new ArrayList<>();
        try (PreparedStatement p = conn.prepareStatement(sql)) {
            p.setLong(1, memberId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    // course.findById
    public CourseDTO findById(long courseId) throws SQLException {
        String sql = "SELECT course_id, title, member_id FROM courses WHERE course_id = ?";
        try (PreparedStatement p = conn.prepareStatement(sql)) {
            p.setLong(1, courseId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) return mapRow(rs);
        }
        return null;
    }

    // course.searchByTitle
    public List<CourseDTO> searchByTitle(String keyword) throws SQLException {
        String sql = "SELECT course_id, title, member_id FROM courses WHERE title LIKE ?";
        List<CourseDTO> list = new ArrayList<>();
        try (PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, "%" + keyword + "%");
            ResultSet rs = p.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    // course.insert
    public long insert(CourseDTO course) throws SQLException {
        String sql = "INSERT INTO courses (title, member_id) VALUES (?, ?)";
        try (PreparedStatement p = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            p.setString(1, course.getTitle());
            p.setLong(2, course.getMemberId());
            p.executeUpdate();
            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) return rs.getLong(1);
        }
        return -1;
    }

    // course.update
    public boolean update(CourseDTO course) throws SQLException {
        String sql = "UPDATE courses SET title = ? WHERE course_id = ?";
        try (PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, course.getTitle());
            p.setLong(2, course.getCourseId());
            return p.executeUpdate() > 0;
        }
    }

    // course.delete
    public boolean delete(long courseId) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (PreparedStatement p = conn.prepareStatement(sql)) {
            p.setLong(1, courseId);
            return p.executeUpdate() > 0;
        }
    }

    private CourseDTO mapRow(ResultSet rs) throws SQLException {
        return new CourseDTO(
                rs.getLong("course_id"),
                rs.getString("title"),
                rs.getLong("member_id")
        );
    }
}
