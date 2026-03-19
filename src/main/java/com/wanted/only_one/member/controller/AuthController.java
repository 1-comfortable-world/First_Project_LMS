package com.wanted.only_one.member.controller;

import com.wanted.only_one.member.service.AuthService;

public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // 강사, 학생 회원가입
    public boolean signUp(String name, String email, String password, String role) {
        return service.signUp(name, email, password, role);
    }

    // 강사 ,학생 로그인
    public boolean signIn(String email, String password) {
        return service.signIn(email, password);
    }
    // 비번 초기화
    public boolean resetPassword(String email, String newPassword) {
        return service.resetPassword(email, newPassword);
    }

    // 강사 . 학생 로그아웃
    public boolean signOut(long memberId) {
        return service.signOut(memberId);
    }

    // 학생 중복 로그인 시 블랙리스트 처리
    public boolean addBlacklist(long memberId) {
        return service.addBlacklist(memberId);
    }
}