package com.shen.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * 根据用户类型，forward到不同的页面，student和teacher页面相同，admin页面不同
     * @param userType 用户类型
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(@SessionAttribute("userType") String userType) {
        if(userType.equals("0") || userType.equals("1")) {
            logger.info(this.getClass().getName(), "  forward:/user.mvc");
            return "forward:/user.mvc";
        }

        if(userType.equals("2")) {
            logger.info(this.getClass().getName(), "  forward:/admin.mvc");
            return "forward:/admin.mvc";
        }
        return "0";
    }

    @RequestMapping(value = "/user")
    public ModelAndView teacherIndex() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/user/index");

        return modelAndView;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView adminIndex() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/admin/index");

        return modelAndView;
    }
}
