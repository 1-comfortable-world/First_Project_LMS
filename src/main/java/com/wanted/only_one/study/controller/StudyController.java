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

    public int addFavList(long memberId, long courseId) {
        return favService.addFavList(memberId, courseId);
    }

    public List<FavDTO> showFavList(long memberId) {
        return favService.showFavList(memberId);
    }

    public List<CourseDTO> showcompletedCourseList(long memberId) {
        return reviewService.showcompletedCourseList(memberId);
    }

    public Boolean WriteReview(long memberId, String description, String content, Double rating) {
        return reviewService.WriteReview(memberId, description, content, rating);
    }

    public List<CourseDTO> showMyStudyingList(long memberId,int menu) {
        return studyingService.showMyStudyingList(memberId,menu);
    }

/*강의 수강완료 처리할 때
studyController.updateCourseStatus(memberId, courseId);
같이 호출*/
    public void updateCourseStatus(long memberId, long courseId) {
        studyingService.updateCourseStatus(memberId, courseId);
    }

    public void completeLecture(long memberId, long lectureId, long courseId) {
        studyingService.completeLecture(memberId, lectureId, courseId);
    }

    public void enrollCourse(long memberId, long courseId) {
        studyingService.enrollCourse(memberId, courseId);
    }

    public List<ReviewDTO> showMyReviewList(long memberId) {
        return reviewService.showMyReviewList(memberId);
    }

    public List<ReviewDTO> ShowReviewInCourse(String description) {
        return reviewService.ShowReviewInCourse(description);
    }

    public List<ReviewDTO> ShowReviewForTeacher(long memberId) {
        return reviewService.ShowReviewForTeacher(memberId);
    }

    public boolean checkCourseExists(String description) {
        return reviewService.checkCourseExists(description);
    }

    public Boolean deleteFavList(long memberId, long courseId) {
        return favService.deleteFavList(memberId, courseId);
    }

    public List<CourseDTO> searchCourseByTitle(String keyword) {
        return favService.searchCourseByTitle(keyword);
    }
}
