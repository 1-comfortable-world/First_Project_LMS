package com.wanted.only_one.study.controller;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.dto.FavDTO;
import com.wanted.only_one.study.service.FavService;

import java.util.List;

public class StudyController {

    private final FavService favService;

    public StudyController(FavService favService) {
        this.favService = favService;
    }

    public List<CourseDTO> showCourseList() {
        return favService.showCourseList();
    }

    public boolean addFavList(String description) {
        return favService.addFavList(description);
    }

    public List<FavDTO> showFavList() {
        return favService.showFavList();
    }
}
