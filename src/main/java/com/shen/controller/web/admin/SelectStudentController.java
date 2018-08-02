package com.shen.controller.web.admin;

import com.google.gson.Gson;
import com.shen.model.user.Student;
import com.shen.mybatis.mappers.student.StudentMapper;
import com.shen.service.admin.AdminService;
import com.shen.utils.ApplicationContextUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class SelectStudentController {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping(value = "/selectStudent")
    public ModelAndView selectStudent(){
        return new ModelAndView("/admin/selectStudent");
    }

    @RequestMapping(value = "/selectStudentInformation", produces = "text/json;charset=GBK")
    @ResponseBody
    public String selectStudentInformation(@RequestParam("offset")String offset, @RequestParam("limit")String limit, HttpServletRequest request){
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();

        AdminService adminService = applicationContext.getBean(AdminService.class);

        List<Student> students = adminService.selectStudentInformation(offset,limit,
                request.getParameter("Id"),
                request.getParameter("name"),
                request.getParameter("classId"),
                request.getParameter("sex"),
                request.getParameter("major"),
                request.getParameter("college"));
        int count = students.size();

        HashMap<Object,Object> map = new HashMap<Object, Object>();
        map.put("count", count);
        map.put("studentInformations", students);

        Gson gson = new Gson();

        return gson.toJson(map);
    }
}
