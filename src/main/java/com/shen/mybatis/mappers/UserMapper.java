package com.shen.mybatis.mappers;

import com.shen.model.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public User selectUser(@Param("id")String id);
    public void insertUser(User user);
    public int validatePassword(@Param("id") String id, @Param("password") String password);
}
