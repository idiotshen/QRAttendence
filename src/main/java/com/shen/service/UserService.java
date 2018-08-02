package com.shen.service;

import com.shen.model.Schedule;
import com.shen.model.Statistics;

import java.util.List;
import java.util.Map;

public interface UserService extends BaseService {
    public List<Schedule> getSchedule(String Id);
    public Map<String, Object> getAttendence(String Id);
    public Map<String, List<Statistics>> getChartStatistics(String Id);
}
