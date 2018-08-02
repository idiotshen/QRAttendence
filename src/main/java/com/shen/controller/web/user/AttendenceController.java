package com.shen.controller.web.user;

import com.google.gson.Gson;
import com.shen.model.user.Student;
import com.shen.model.user.Teacher;
import com.shen.service.student.StudentServiceImp;
import com.shen.service.teacher.TeacherServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AttendenceController {
    @RequestMapping(value = "/attendence")
    public ModelAndView attendence() {
        return new ModelAndView("/user/attendence");
    }

    @RequestMapping(value = "/attendence/studentUnattendence", produces="text/json;charset=GBK")
    @ResponseBody
    public String studentAttendenceDetail(@SessionAttribute("user")Student student) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");

        StudentServiceImp studentServiceImp = applicationContext.getBean(StudentServiceImp.class);

        Gson gson = new Gson();
        return gson.toJson(studentServiceImp.getAttendence(student.getId()));
    }

    @RequestMapping(value = "/attendence/teacherUnattendence", produces="text/json;charset=GBK")
    @ResponseBody
    public String teacherAttendenceDetail(@SessionAttribute("user")Teacher teacher, @RequestParam("lessonId")String lessonId) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");

        TeacherServiceImp teacherService = applicationContext.getBean(TeacherServiceImp.class);

        Gson gson = new Gson();
        return gson.toJson(teacherService.getAttendence(lessonId));
    }
}
