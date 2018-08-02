package com.shen.service;

import com.shen.model.user.User;

public interface BaseService {
    public int validate(String username, String password);
    public User getUser(String username, String password);
}
