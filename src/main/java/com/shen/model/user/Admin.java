package com.shen.model.user;

public class Admin extends User{
    private String Id;
    private String password;

    public String getId() {
        return Id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "Id='" + Id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
