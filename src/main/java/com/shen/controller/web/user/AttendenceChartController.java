package com.shen.controller.web.user;

import com.google.gson.Gson;
import com.shen.model.user.Student;
import com.shen.model.user.Teacher;
import com.shen.model.Statistics;
import com.shen.service.student.StudentServiceImp;
import com.shen.service.teacher.TeacherServiceImp;
import com.shen.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class AttendenceChartController {
    @RequestMapping(value = "/attendence/studentAttendenceChart", produces = "text/json;charset=GBK")
    @ResponseBody
    public String getStudentAttendenceChartGroupByWeek(@SessionAttribute("user")Student student){
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        StudentServiceImp studentServiceImp = applicationContext.getBean(StudentServiceImp.class);

        Map<String, List<Statistics>> chartStatistics = studentServiceImp.getChartStatistics(student.getId());

        Gson gson = new Gson();
        return gson.toJson(chartStatistics);
    }

    @RequestMapping(value = "/attendence/teacherAttendenceChart.mvc", produces = "text/json;charset=GBK")
    @ResponseBody
    public String getTeacherAttendenceChartGroupByWeek(@SessionAttribute("user")Teacher teacher){
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        TeacherServiceImp teacherServiceImp = applicationContext.getBean(TeacherServiceImp.class);

        Gson gson = new Gson();

        Map<String, List<Statistics>> statistices = teacherServiceImp.getChartStatistics(teacher.getId());

        return gson.toJson(statistices);
    }
}
