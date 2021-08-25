package com.example.arrow;

public class User {

    private String email;
    private String password;
    private String fName;
    private String lName;
    private String college;

    private int sync;
    private int attendance;
    private int grading;

    private int[] featured;
    private int[] current;

    public User(String email, String password, String fName, String lName, String college, int sync, int attendance, int grading, int[] featured, int[] current) {
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.college = college;
        this.sync = sync;
        this.attendance = attendance;
        this.grading = grading;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getCollege() {
        return college;
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

    public int[] getFeatured() {
        return featured;
    }

    public int[] getCurrent() {
        return current;
    }
}
