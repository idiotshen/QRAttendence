package com.shen.model.user;

public class Teacher extends User{
    private String Id;
    private String name;
    private String major;
    private String college;
    private String password;

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setCollege(String college) {
        this.college = college;
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

    public String getMajor() {
        return major;
    }

    public String getCollege() {
        return college;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Id='" + Id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                '}';
    }
}
