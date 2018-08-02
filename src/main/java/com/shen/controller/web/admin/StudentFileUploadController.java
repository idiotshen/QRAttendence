package com.shen.controller.web.admin;

import com.shen.service.admin.AdminService;
import com.shen.utils.ApplicationContextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class StudentFileUploadController {
    static Logger logger = LogManager.getLogger(StudentFileUploadController.class.getName());

    @RequestMapping(value = "/studentFileUploader",produces = "text/json;charset=GBK")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        AdminService adminService = applicationContext.getBean(AdminService.class);

        return adminService.studentFileUpload(file);
    }
}
