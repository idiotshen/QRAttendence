package com.shen.mybatis.mappers.student;

import com.shen.model.user.Student;
import com.shen.mybatis.mappers.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends UserMapper {
    public List<Student> selectRowBoundsStudent(RowBounds rowBounds,
                                                @Param("Id")String Id,
                                                @Param("name")String name,
                                                @Param("classId")String classId,
                                                @Param("sex")String sex,
                                                @Param("major")String major,
                                                @Param("college")String college);
    public int getCount();
}
