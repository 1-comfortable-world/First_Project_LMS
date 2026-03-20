package com.wanted.only_one.member.dao;

import com.wanted.only_one.global.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// 접속 확인하는거
public class ConnectionDAO {
    private Connection con;
    public ConnectionDAO(Connection con){
        this.con = con;

    }


    public boolean insertConnection(long memberId) {
        String sql = "INSERT INTO connection_status (member_id) VALUES (?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, memberId);

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;

    }

    // 접속 삭제확인하느거
    public boolean deleteConnection(long memberId) {
        String sql = "DELETE FROM connection_status WHERE member_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, memberId);

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }

    // 접속 중인지 확인하는거
    public boolean existsConnection(long memberId) {
        String sql = "SELECT COUNT(*) FROM connection_status WHERE member_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }


    public void logout(String email) throws SQLException {
        QueryUtil.getQuery("member.logout");
        PreparedStatement pstmt = null;
        pstmt.setString(1, email);
    }
}
