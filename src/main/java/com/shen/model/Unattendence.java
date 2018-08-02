package com.shen.model;

public class Unattendence {
    private String studentId;
    private String lessonId;
    private String dday;
    private String classroomId;
    private String dtime;
    private int week;

    public String getStudentId() {
        return studentId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getDday() {
        return dday;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public String getDtime() {
        return dtime;
    }

    public int getWeek() {
        return week;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public void setDday(String dday) {
        this.dday = dday;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
