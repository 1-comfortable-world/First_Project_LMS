package com.wanted.only_one.member.dto;

import java.time.LocalDateTime;

public class MemberDTO {
    private long memberid;
    private String name;
    private String email;
    private String password;
    private String role;
    private LocalDateTime enrolledAt;
    private int accCount;

    public MemberDTO() {}


    public MemberDTO(long memberid, String name, String email, String password, String role, LocalDateTime enrolledAt, int accCount) {
        this.memberid = memberid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enrolledAt = enrolledAt;
        this.accCount = accCount;
    }

    public long getMemberid() {
        return memberid;
    }

    public void setMemberid(long memberid) {
        this.memberid = memberid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public int getAccCount() {
        return accCount;
    }

    public void setAccCount(int accCount) {
        this.accCount = accCount;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberid=" + memberid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enrolledAt=" + enrolledAt +
                ", accCount=" + accCount +
                '}';
    }

    public long getMemberId() {
        return 0;
    }
}
