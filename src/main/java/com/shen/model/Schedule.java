package com.shen.model;

public class Schedule {
    private String lessonId;
    private String classId;
    private String classroomId;
    private String dday;
    private String dtime;
    private String courseName;
    private String teacherName;

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public void setDday(String dday) {
        this.dday = dday;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public String getDday() {
        return dday;
    }

    public String getDtime() {
        return dtime;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
