package com.shen.mybatis.mappers.schedule;

import com.shen.model.Schedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleMapper {
    public List<Schedule> selectStudentSchedule(@Param("classid") String classid);
    public List<Schedule> selectTeacherSchedule(@Param("teacherid") String teacherid);
    public Schedule selectSchedule(@Param("lessonId")String lessonId);
}
