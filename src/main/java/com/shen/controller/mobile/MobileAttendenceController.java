package com.shen.controller.mobile;

import com.google.gson.Gson;
import com.shen.mybatis.mappers.attendence.AttendenceMapper;
import com.shen.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MobileAttendenceController {
    @RequestMapping(value = "/android/attend", produces = "text/json;charset=GBK")
    @ResponseBody
    public String login(@RequestParam("lessonId") String lessonId, @RequestParam("studentId") String studentId, @RequestParam("week")String week) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();

        AttendenceMapper attendenceMapper = applicationContext.getBean(AttendenceMapper.class);

        int rows = attendenceMapper.insertAttendence(lessonId, studentId, week);

        Gson gson = new Gson();

        return gson.toJson(rows);
    }
}
