package com.example.arrow;

public class CommentHelperClass {
    String fName, lName, course, learning, attendance, grading, review;

    public CommentHelperClass(String fName, String lName, String course, String learning, String attendance, String grading, String review) {
        this.fName = fName;
        this.lName = lName;
        this.course = course;
        this.learning = learning;
        this.attendance = attendance;
        this.grading = grading;
        this.review = review;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getGrading() {
        return grading;
    }

    public void setGrading(String grading) {
        this.grading = grading;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
