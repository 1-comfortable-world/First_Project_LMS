package com.wanted.only_one.study.dto;

public class ReviewDTO {

    private Long review_id;
    private Long course_id;
    private Long member_id;
    private String contents;
    private String title;
    private Double rating;

    public ReviewDTO() {
    }

    public ReviewDTO(Long review_id, Long course_id, Long member_id, String contents, Double rating) {
        this.review_id = review_id;
        this.course_id = course_id;
        this.member_id = member_id;
        this.contents = contents;
        this.rating = rating;
    }

    public ReviewDTO(String title, String contents, Double rating) {
        this.title = title;
        this.contents = contents;
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "review_id=" + review_id +
                ", course_id=" + course_id +
                ", member_id=" + member_id +
                ", contents='" + contents + '\'' +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                '}';
    }
}
