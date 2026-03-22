package com.wanted.only_one.study.dao;

import com.wanted.only_one.global.utils.QueryUtil;
import com.wanted.only_one.study.dto.FavDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavDAO {

    private final Connection connection;

    public FavDAO(Connection connection) {
        this.connection = connection;
    }


// 전체 선택
    public List<FavDTO> showFavList(long memberId) throws SQLException{
        // 동작시킬 쿼리문 준비
        String query = QueryUtil.getQuery("showFavList");
        List<FavDTO> favList = new ArrayList<>();

        // 쿼리문 동작
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, memberId); // TODO: 로그인 세션 연결 후 교체 -> getMember_id 등 setter로
            ResultSet rset = pstmt.executeQuery();

            while(rset.next()){
                FavDTO course = new FavDTO(
                        rset.getString("course_title"),
                        rset.getString("member_name")
                );
                favList.add(course);
            }
        }
        return favList;
    }

    public Long addFav(long memberId,String description) throws SQLException {
        String query = QueryUtil.getQuery("addFavList");

        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1,memberId);  // 나중에 현재 로그인 되어있는 사용자의 member_id 를 삽입
            pstmt.setString(2, description);

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

    public Boolean deleteFav(long memberId, String description) throws SQLException {
        String query = QueryUtil.getQuery("deleteFavList");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, memberId);
            pstmt.setString(2, description);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0 ? true : null;
        }
    }

    public boolean checkAlreadyFav(long memberId, String description) throws SQLException {
        String query = QueryUtil.getQuery("checkAlreadyFav");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, memberId);
            pstmt.setString(2, description);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public boolean checkAlreadyStudying(long memberId, String description) throws SQLException {
        String query = QueryUtil.getQuery("checkAlreadyStudying");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, memberId);
            pstmt.setString(2, description);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }
}
