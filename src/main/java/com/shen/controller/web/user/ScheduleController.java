package com.shen.controller.web.user;

import com.google.gson.Gson;
import com.shen.model.Schedule;
import com.shen.model.user.Student;
import com.shen.model.user.Teacher;
import com.shen.service.student.StudentServiceImp;
import com.shen.service.teacher.TeacherServiceImp;
import com.shen.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ScheduleController {
    @RequestMapping(value = "/schedule")
    public ModelAndView schedule() {
        return new ModelAndView("/user/schedule");
    }

    @RequestMapping(value = "/scheduleDetail")
    public String schedule(@SessionAttribute("userType") String type){
        switch (type) {
            case "0":
                return "forward:/schedule/studentDetail.mvc";
            case "1":
                return "forward:/schedule/teacherDetail.mvc";
            default:
                return "0";
        }
    }

    @RequestMapping(value = "/schedule/studentDetail", produces = "text/json;charset=GBK")
    @ResponseBody
    public String studentScheduleDetail(@SessionAttribute("user")Student student) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        StudentServiceImp studentServiceImp = applicationContext.getBean(StudentServiceImp.class);

        List<Schedule> scheduleList = studentServiceImp.getSchedule(student.getClassId());

        Gson gson = new Gson();
        return gson.toJson(scheduleList);
    }

    @RequestMapping(value = "/schedule/teacherDetail", produces = "text/json;charset=GBK")
    @ResponseBody
    public String teacherScheduleDetail(@SessionAttribute("user")Teacher teacher) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
        TeacherServiceImp teacherService = applicationContext.getBean(TeacherServiceImp.class);

        List<Schedule> scheduleList = teacherService.getSchedule(teacher.getId());

        Gson gson = new Gson();
        return gson.toJson(scheduleList);
    }
}
