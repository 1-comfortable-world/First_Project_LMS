package com.wanted.only_one.study.controller;

import com.wanted.only_one.course.dto.CourseDTO;
import com.wanted.only_one.study.dto.FavDTO;
import com.wanted.only_one.study.dto.ReviewDTO;
import com.wanted.only_one.study.service.FavService;
import com.wanted.only_one.study.service.ReviewService;
import com.wanted.only_one.study.service.StudyingService;

import java.util.List;

public class StudyController {

    private final FavService favService;
    private final ReviewService reviewService;
    private final StudyingService studyingService;

    public StudyController(FavService favService, ReviewService reviewService, StudyingService studyingService) {
        this.favService = favService;
        this.reviewService = reviewService;
        this.studyingService = studyingService;
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

    public List<CourseDTO> showMyStudyingList(int menu) {
        return studyingService.showMyStudyingList(menu);
    }

/*강의 수강완료 처리할 때
studyController.updateCourseStatus(memberId, courseId);
같이 호출*/
    public void updateCourseStatus(long memberId, long courseId) {
        studyingService.updateCourseStatus(memberId, courseId);
    }

    public List<ReviewDTO> showMyReviewList() {
        return reviewService.showMyReviewList();
    }

    public List<ReviewDTO> ShowReviewInCourse(String description) {
        return reviewService.ShowReviewInCourse(description);
    }

    public List<ReviewDTO> ShowReviewForTeacher() {
        return reviewService.ShowReviewForTeacher();
    }

    public boolean checkCourseExists(String description) {
        return reviewService.checkCourseExists(description);
    }
}
