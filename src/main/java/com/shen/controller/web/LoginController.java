package com.shen.controller.web;

import com.shen.model.user.User;
import com.shen.service.BaseService;
import com.shen.service.admin.AdminService;
import com.shen.service.student.StudentServiceImp;
import com.shen.service.teacher.TeacherServiceImp;
import com.shen.utils.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class.getName());

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("flag", false);
        return modelAndView;
    }

    /**
     * 根据用户类型，验证用户名密码
     * @param userType 用户类型 0代表学生 1代表老师 2代表管理员
     * @return forward字符串
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validate(@RequestParam("userType") String userType) {
        if(userType.equals("0")) {
            return "forward:/validateStudent.mvc";
        }

        if(userType.equals("1")) {
            return "forward:/validateTeacher.mvc";
        }

        if(userType.equals("2")) {
            return "forward:/validateAdmin.mvc";
        }
        return "0";
    }

    /**
     * 相应教师登陆
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型
     * @param httpServletRequest 对应请求
     * @return 指向的网页
     */
    @RequestMapping(value = "/validateTeacher")
    public String teacherIndex(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("userType") String userType, HttpServletRequest httpServletRequest) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        TeacherServiceImp teacherService = applicationContext.getBean(TeacherServiceImp.class);

        return validateAndForward(teacherService, username, password, userType, httpServletRequest.getSession());
    }

    /**
     * 响应学生登陆
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型
     * @param httpServletRequest 对应请求
     * @return 指向的网页
     */
    @RequestMapping(value = "/validateStudent")
    public String studentIndex(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("userType") String userType, HttpServletRequest httpServletRequest) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        StudentServiceImp studentServiceImp = applicationContext.getBean(StudentServiceImp.class);

        return validateAndForward(studentServiceImp, username, password, userType, httpServletRequest.getSession());
    }

    /**
     * 响应管理员登陆
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型
     * @param httpServletRequest 对应请求
     * @return 指向的网页
     */
    @RequestMapping(value = "/validateAdmin")
    public String adminIndex(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("userType") String userType, HttpServletRequest httpServletRequest) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        AdminService adminService = applicationContext.getBean(AdminService.class);

        return validateAndForward(adminService, username, password, userType, httpServletRequest.getSession());
    }

    /**
     * 实现登陆功能
     * @param baseService 需要被调用的接口
     * @param username 用户名
     * @param password 密码
     * @param userType 用户类型
     * @param session 会话
     * @return redirect的字符串
     */
    private String validateAndForward(BaseService baseService, String username, String password, String userType, HttpSession session) {
        // 调用 baseService的validate方法，如果返回是1，则说明用户名密码正确
        if(baseService.validate(username, password) == 1) {
            User user = baseService.getUser(username, password);

            // 将用户对象放入会话作用域
            session.setAttribute("user", user);

            // 将用户类型放入会话作用域
            session.setAttribute("userType", userType);

            logger.info(this.getClass().getName() + "username:" + username + " password:" + password + "userType:" + userType + "验证通过");

            // 指向首页
            return "redirect:/index.mvc";
        } else {

            // 表示用户名或密码错误
            session.setAttribute("flag", true);

            // 指向登陆页面
            return "redirect:/login.mvc";
        }
    }
}
