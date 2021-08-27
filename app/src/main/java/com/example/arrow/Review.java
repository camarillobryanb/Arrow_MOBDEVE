package com.example.arrow;

public class Review {

    private int sync;
    private int attendance;
    private int grading;
    private float overall;
    private String comment;

    public Review(int sync, int attendance, int grading, float overall, String comment) {
        this.sync = sync;
        this.attendance = attendance;
        this.grading = grading;
        this.overall = overall;
        this.comment = comment;
    }

    public int getSync() {
        return sync;
    }

    public int getAttendance() {
        return attendance;
    }

    public int getGrading() {
        return grading;
    }

    public float getOverall() {
        return overall;
    }

    public String getComment() {
        return comment;
    }
}
