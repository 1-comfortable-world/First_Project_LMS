package com.wanted.only_one.course.dto;
public class CourseDTO {
    private String title; // 추가

    public CourseDTO(String title) {
        this.title = title;
    }

    public String getTitle() { // 추가
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}