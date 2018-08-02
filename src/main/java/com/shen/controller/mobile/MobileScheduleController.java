package com.shen.controller.mobile;

import com.google.gson.Gson;
import com.shen.model.Schedule;
import com.shen.mybatis.mappers.schedule.ScheduleMapper;
import com.shen.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MobileScheduleController {
    @RequestMapping(value = "/android/getScheduleDetail", produces = "text/json;charset=GBK")
    @ResponseBody
    public String getScheduleDetail(@RequestParam("lessonId")String lessonId){
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        ScheduleMapper scheduleMapper = applicationContext.getBean(ScheduleMapper.class);

        Schedule schedule = scheduleMapper.selectSchedule(lessonId);

        Gson gson = new Gson();

        return gson.toJson(schedule);
    }
}
