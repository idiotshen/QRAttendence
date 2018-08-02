package com.shen.controller.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonalController {
    @RequestMapping(value = "/personal")
    public ModelAndView personalInformation() {
        return new ModelAndView("/user/personalInformation");
    }
}
