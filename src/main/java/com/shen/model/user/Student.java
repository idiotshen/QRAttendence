package com.shen.model.user;

import java.util.Objects;

public class Student extends User{
    private String Id;
    private String password;
    private String name;
    private String classId;
    private String major;
    private String college;
    private String sex;

    public void setId(String id) {
        Id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return Id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getClassId() {
        return classId;
    }

    public String getMajor() {
        return major;
    }

    public String getCollege() {
        return college;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(Id, student.Id) &&
                Objects.equals(password, student.password) &&
                Objects.equals(name, student.name) &&
                Objects.equals(classId, student.classId) &&
                Objects.equals(major, student.major) &&
                Objects.equals(college, student.college) &&
                Objects.equals(sex, student.sex);
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id='" + Id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", classId='" + classId + '\'' +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
