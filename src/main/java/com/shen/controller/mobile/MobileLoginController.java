package com.shen.controller.mobile;

import com.google.gson.Gson;
import com.shen.model.user.Student;
import com.shen.model.user.User;
import com.shen.mybatis.mappers.student.StudentMapper;
import com.shen.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MobileLoginController {
    @RequestMapping(value = "/android/selectStudentInformation", produces = "text/json;charset=GBK")
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);
        int flag = studentMapper.validatePassword(username, password);

        Gson gson = new Gson();
        Map<Object, Object> returnJson = new HashMap<>();

        if (flag == 1) {
            User student = studentMapper.selectUser(username);
            returnJson.put("student", student);
            returnJson.put("status", "1");

        } else {
            returnJson.put("status", "0");
            returnJson.put("student", null);
        }

        return gson.toJson(returnJson);
    }
}
