package com.shen.controller.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminStudentController {
    @RequestMapping(value = "/adminStudent")
    public ModelAndView personalInformation() {
        return new ModelAndView("/admin/adminStudent");
    }
}
