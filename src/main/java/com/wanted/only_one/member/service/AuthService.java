package com.wanted.only_one.member.service;

import com.wanted.only_one.global.config.JDBCTemplate;
import com.wanted.only_one.member.dao.BlacklistDAO;
import com.wanted.only_one.member.dao.ConnectionDAO;
import com.wanted.only_one.member.dao.MemberDAO;
import com.wanted.only_one.member.dto.MemberDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthService {

    private MemberDAO memberDAO;
    private Connection con;

    public AuthService(Connection connection) {
        this.memberDAO = new MemberDAO(connection);
        this.con = con;
    }

    // 1. 회원가입
    public boolean signUp(String name, String email, String password, String role) {


        try {
            con = JDBCTemplate.getConnection();
            MemberDAO memberDAO = new MemberDAO(con);

            // 이메일 중복 확인
            if (memberDAO.existsByEmail(email)) {
                System.out.println("이미 사용 중인 이메일입니다.");
                return false;
            }

            // 회원가입 INSERT
            return memberDAO.insertMember(name, email, password, role);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // 2. 로그인
    public boolean signIn(String email, String password) {


        try {
            con = JDBCTemplate.getConnection();
            MemberDAO memberDAO = new MemberDAO(con);
            BlacklistDAO blacklistDAO = new BlacklistDAO(con);
            ConnectionDAO connectionDAO = new ConnectionDAO(con);

            //  회원 조회
            MemberDTO member = memberDAO.findByEmailAndPassword(email, password);
            if (member == null) {
                System.out.println("이메일 또는 비밀번호가 틀렸습니다.");
            }

            // 블랙리스트 확인
            if (blacklistDAO.existsBlacklist(member.getMemberId())) {
                System.out.println("블랙리스트 회원입니다. 로그인이 불가합니다.");
                return false;
            }

            //  중복 로그인 확인
            if (connectionDAO.existsConnection(member.getMemberId())) {
                // acc_count + 1
                memberDAO.updateAccCount(member.getMemberId());

                // acc_count 3 이상이면 블랙리스트 등록
                if (member.getAccCount() + 1 >= 3) {
                    blacklistDAO.insertBlacklist(member.getMemberId());
                    System.out.println("중복 로그인 3회 이상으로 블랙리스트 처리되었습니다.");
                } else {
                    System.out.println("이미 접속 중인 계정입니다.");
                }
                return false;
            }

            //  접속 등록
            return connectionDAO.insertConnection(member.getMemberId());

        } catch (SQLException e) {
            System.out.println("로딩 중 오류 발생");
            return false;
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // 로그아웃
    public boolean signOut(long memberId) {
        Connection conn = null;

        try {
            conn = JDBCTemplate.getConnection();
            ConnectionDAO connectionDAO = new ConnectionDAO(conn);

            return connectionDAO.deleteConnection(memberId);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // 블랙리스트 추가
    public boolean addBlacklist(long memberId) {
        Connection conn = null;

        try {
            conn = JDBCTemplate.getConnection();
            BlacklistDAO blacklistDAO = new BlacklistDAO(conn);

            return blacklistDAO.insertBlacklist(memberId);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // 비밀번호 초기화
    public boolean resetPassword(String email, String newPassword) {
        Connection conn = null;

        try {
            conn = JDBCTemplate.getConnection();
            MemberDAO memberDAO = new MemberDAO(conn);

            // 이메일로 회원 존재 확인
            if (!memberDAO.existsByEmail(email)) {
                System.out.println("존재하지 않는 이메일입니다.");
                return false;
            }

            // 새 비밀번호로 UPDATE
            return memberDAO.resetPasswordByEmail(email, newPassword);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public boolean emailMix(String email) {
        return memberDAO.emailMix(email);
    }


    public boolean pwdCheck(String email, String password) {
        return memberDAO.pwdCheck(email,password);
    }
}