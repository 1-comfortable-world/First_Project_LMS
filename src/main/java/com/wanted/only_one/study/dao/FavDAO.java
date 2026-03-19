package com.wanted.only_one.study.dao;

import com.wanted.only_one.global.utils.QueryUtil;
import com.wanted.only_one.study.dto.FavDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavDAO {

    private final Connection connection;

    public FavDAO(Connection connection) {
        this.connection = connection;
    }


// 전체 선택
    public List<FavDTO> showFavList() throws SQLException{
        // 동작시킬 쿼리문 준비
        String query = QueryUtil.getQuery("showFavList");
        List<FavDTO> favList = new ArrayList<>();

        // 쿼리문 동작
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, 6L); // TODO: 로그인 세션 연결 후 교체 -> getMember_id 등 setter로
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

    public Long addFav(String description) throws SQLException {
        String query = QueryUtil.getQuery("addFavList");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1,1);  // 나중에 현재 로그인 되어있는 사용자의 member_id 를 삽입
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
}
