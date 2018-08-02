package com.shen.service.student;

import com.shen.model.Schedule;
import com.shen.model.Unattendence;
import com.shen.model.UnattendenceStatistics;
import com.shen.model.user.User;
import com.shen.mybatis.mappers.attendence.AttendenceMapper;
import com.shen.mybatis.mappers.attendenceChart.AttendenceChartMapper;
import com.shen.model.Statistics;
import com.shen.mybatis.mappers.schedule.ScheduleMapper;
import com.shen.mybatis.mappers.student.StudentMapper;
import com.shen.service.UserService;
import com.shen.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImp implements UserService {

    /**
     * 根据学生id,返回学生的课程表列表
     * @param Id 学生id
     * @return 课程列表
     */
    @Override
    public List<Schedule> getSchedule(String Id) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        ScheduleMapper scheduleMapper = applicationContext.getBean(ScheduleMapper.class);

        return scheduleMapper.selectStudentSchedule(Id);
    }

    /**
     * 根据学生id 返回缺课内容的map，包括缺课列表，
     * 所缺课程编号的集合，所缺课程星期的集合，所缺课程教室的集合，所缺课程星期的集合，所缺课程周数的集合，
     * 所缺课程的统计，包括每周所缺课程的统计，每门课程缺课的统计。
     * 上述集合是为了方便前端做筛选栏
     * @param Id 学生id
     * @return 缺课信息map
     */
    @Override
    public Map<String, Object> getAttendence(String Id) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
        AttendenceMapper attendenceMapper = applicationContext.getBean(AttendenceMapper.class);

        List<Unattendence> unattendenceList = attendenceMapper.selectStudentUnattendences(Id);
        List<String> lessonIds = attendenceMapper.selectStudentUnattendenceLessonIds(Id);
        List<String> classroomIds = attendenceMapper.selectStudentUnattendenceClassroomIds(Id);
        List<String> ddays = attendenceMapper.selectStudentUnattendenceDdays(Id);
        List<String> dtimes= attendenceMapper.selectStudentUnattendenceDtimes(Id);
        List<String> weeks = attendenceMapper.selectStudentUnattendenceWeeks(Id);
        List<UnattendenceStatistics> unattendenceStatistices= attendenceMapper.selectStudentUnattendenceStatistices(Id);

        HashMap<String,Object> unattendenceDetail = new HashMap<>();
        unattendenceDetail.put("unattendences", unattendenceList);
        unattendenceDetail.put("lessonIds", lessonIds);
        unattendenceDetail.put("classroomIds", classroomIds);
        unattendenceDetail.put("ddays", ddays);
        unattendenceDetail.put("dtimes",dtimes);
        unattendenceDetail.put("weeks", weeks);
        unattendenceDetail.put("unattendenceStatistices", unattendenceStatistices);
        
        return unattendenceDetail;
    }

    @Override
    public Map<String, List<Statistics>> getChartStatistics(String Id) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        AttendenceChartMapper attendenceChartMapper = applicationContext.getBean(AttendenceChartMapper.class);

        List<Statistics> weekStatistics = attendenceChartMapper.selectStudentWeekStatistices(Id);

        HashMap<String, List<Statistics>> statistics = new HashMap<>();
        statistics.put("weekStatistices", weekStatistics);

        return statistics;
    }

    /**
     * 验证用户名与密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return 标志位 1 表示匹配，0表示不匹配
     */
    @Override
    public int validate(String username, String password) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);

        return studentMapper.validatePassword(username, password);
    }

    /**
     * 根据用户名与密码，返回用户对象
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    @Override
    public User getUser(String username, String password) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);

        return studentMapper.selectUser(username);
    }
}
