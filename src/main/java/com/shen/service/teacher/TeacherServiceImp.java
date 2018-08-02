package com.shen.service.teacher;

import com.shen.model.Schedule;
import com.shen.model.user.Student;
import com.shen.model.user.User;
import com.shen.mybatis.mappers.attendence.AttendenceMapper;
import com.shen.mybatis.mappers.attendenceChart.AttendenceChartMapper;
import com.shen.model.Statistics;
import com.shen.mybatis.mappers.schedule.ScheduleMapper;
import com.shen.mybatis.mappers.student.StudentMapper;
import com.shen.mybatis.mappers.teacher.TeacherMapper;
import com.shen.service.UserService;
import com.shen.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherServiceImp implements UserService {
    @Override
    public List<Schedule> getSchedule(String Id) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        ScheduleMapper scheduleMapper = applicationContext.getBean(ScheduleMapper.class);

        return scheduleMapper.selectTeacherSchedule(Id);
    }


    @Override
    public HashMap<String, Object> getAttendence(String lessonId) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
        AttendenceMapper attendenceMapper = applicationContext.getBean(AttendenceMapper.class);
        StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);

        HashMap<String,Object> map = new HashMap<>();

        List<String> studentIds = attendenceMapper.selectStudentList(lessonId);

        List<Object> unattendenceList = new ArrayList<>();

        for (String studentId : studentIds) {
            Student student = (Student) studentMapper.selectUser(studentId);
            List<String> unattendenceWeek = attendenceMapper.selectStudentAttendence(studentId, lessonId);

            Map<String, Object> unattendenceDetail = new HashMap<>();
            unattendenceDetail.put("name",student.getName());
            unattendenceDetail.put("unattendenceWeek",unattendenceWeek);
            unattendenceList.add(unattendenceDetail);
        }

        map.put("unattendences", unattendenceList);
        return map;
    }

    @Override
    public Map<String, List<Statistics>> getChartStatistics(String Id) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        AttendenceChartMapper attendenceChartMapper = applicationContext.getBean(AttendenceChartMapper.class);

        List<Statistics> weekStatistices = attendenceChartMapper.selectTeacherWeekStatistices(Id);
        List<Statistics> lessonStatistices = attendenceChartMapper.selectTeacherLessonStatistics(Id);

        HashMap<String, List<Statistics>> statistics = new HashMap<>();
        statistics.put("weekStatistices", weekStatistices);
        statistics.put("lessonStatistices", lessonStatistices);

        return statistics;
    }

    @Override
    public int validate(String username, String password) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        TeacherMapper teacherMapper = applicationContext.getBean(TeacherMapper.class);

        return teacherMapper.validatePassword(username, password);
    }

    @Override
    public User getUser(String username, String password) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        TeacherMapper teacherMapper = applicationContext.getBean(TeacherMapper.class);

        return teacherMapper.selectUser(username);
    }
}
