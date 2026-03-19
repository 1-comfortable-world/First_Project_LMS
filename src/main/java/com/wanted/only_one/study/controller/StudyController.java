package com.wanted.only_one.study.controller;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.dto.FavDTO;
import com.wanted.only_one.study.dto.StudyingDTO;
import com.wanted.only_one.study.service.FavService;
import com.wanted.only_one.study.service.ReviewService;

import java.util.List;

public class StudyController {

    private final FavService favService;
    private final ReviewService reviewService;

    public StudyController(FavService favService, ReviewService reviewService) {
        this.favService = favService;
        this.reviewService = reviewService;
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

    public List<CourseDTO> showcompletedCourseList() {
        return reviewService.showcompletedCourseList();
    }

    public Boolean WriteReview(String description, String content, Double rating) {
        return reviewService.WriteReview(description,content,rating);

    }
}
