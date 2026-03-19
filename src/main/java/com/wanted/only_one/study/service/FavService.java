package com.wanted.only_one.study.service;

import com.wanted.only_one.course.dao.CourseDAO;
import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.dao.FavDAO;
import com.wanted.only_one.study.dto.FavDTO;
import com.wanted.only_one.study.dto.ReviewDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FavService {

    private final FavDAO favDAO;
    private final Connection connection;
    private final CourseDAO courseDAO;


    public FavService(Connection connection) {
        this.favDAO = new FavDAO(connection);
        this.connection = connection;
        this.courseDAO = new CourseDAO();
    }

   // 선택 목록에 넣을 강좌 선택을 위한 전체 강좌 목록 조회
    public List<CourseDTO> showCourseList() {
        System.out.println("로그 찍는 용 : 전체 강좌 출력");
    //     return courseDAO.showAllCourses(); // 종준님 강좌 조회 메서드 연결
        return List.of();
    }


    public boolean addFavList(String description){
        try {
            connection.setAutoCommit(false);

            // save(newfavcourse)를 통해 얻은 추가할 과목 목록에의 과목id를 저장
            Long addededCourseId = favDAO.addFav(description);

            if(addededCourseId == null){
                throw new SQLException("🚨강좌 ID 생성에 실패했습니다 ");
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException("롤백 중 에러 발생"+ex);
            }
            return false;
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public List<FavDTO> showFavList() {
        try {
            return favDAO.showFavList();
        } catch (SQLException e) {
            throw new RuntimeException("선택 목록 조최 중 에러 발생 🚨"+e);
        }
    }

}
