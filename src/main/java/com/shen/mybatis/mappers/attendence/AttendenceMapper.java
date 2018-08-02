package com.shen.mybatis.mappers.attendence;

import com.shen.model.Unattendence;
import com.shen.model.UnattendenceStatistics;
import com.shen.model.user.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendenceMapper {
    public List<Unattendence> selectStudentUnattendences(@Param("id") String studentId);
    public List<String> selectStudentUnattendenceLessonIds(@Param("id") String studentId);
    public List<String> selectStudentUnattendenceClassroomIds(@Param("id") String studentId);
    public List<String> selectStudentUnattendenceDdays(@Param("id") String studentId);
    public List<String> selectStudentUnattendenceDtimes(@Param("id") String studentId);
    public List<String> selectStudentUnattendenceWeeks(@Param("id") String studentId);
    public List<UnattendenceStatistics> selectStudentUnattendenceStatistices(@Param("id") String studentId);

    public List<Unattendence> selectTeacherUnattendences(@Param("id") String teacherId);
    public List<UnattendenceStatistics> selectTeacherUnattendenceStatistices(@Param("id") String teacherId);

    public List<String> selectStudentList(@Param("lessonId")String lessonId);
    public List<String> selectStudentAttendence(@Param("Id") String Id, @Param("lessonId") String lessonId);

    public int insertAttendence(@Param("lessonId")String lessonId, @Param("studentId")String studentId, @Param("week") String week);
}
