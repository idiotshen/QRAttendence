package com.shen.mybatis.mappers.teacher;

import com.shen.model.user.Teacher;
import com.shen.model.user.User;
import com.shen.mybatis.mappers.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper extends UserMapper {
}
