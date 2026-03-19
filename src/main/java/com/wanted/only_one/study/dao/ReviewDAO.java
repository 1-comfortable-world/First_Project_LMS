package com.wanted.only_one.study.dao;

import com.wanted.only_one.global.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDAO {


    private final Connection connection;

    public ReviewDAO(Connection connection) {
        this.connection = connection;
    }

    public Long WriteReview(String description, String content, Double rating) throws SQLException{

        String query = QueryUtil.getQuery("writeReview");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1,1);  // 나중에 현재 로그인 되어있는 사용자의 member_id 를 삽입
            pstmt.setString(2, content);
            pstmt.setDouble(3, rating);
            pstmt.setString(4, description);

            int affectedRows = pstmt.executeUpdate();

            if(affectedRows>0){
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }
}


