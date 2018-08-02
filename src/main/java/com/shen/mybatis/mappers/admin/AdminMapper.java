package com.shen.mybatis.mappers.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    public int validatePassword(@Param("Id")String Id, @Param("password")String paasword);
}
