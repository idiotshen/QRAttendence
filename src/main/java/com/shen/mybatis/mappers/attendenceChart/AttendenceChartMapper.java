package com.shen.mybatis.mappers.attendenceChart;

import com.shen.model.Statistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendenceChartMapper {
    public List<Statistics> selectStudentWeekStatistices(@Param("id")String id);

    public List<Statistics> selectTeacherWeekStatistices(@Param("id")String id);

    public List<Statistics> selectTeacherLessonStatistics(@Param("id")String id);
}
