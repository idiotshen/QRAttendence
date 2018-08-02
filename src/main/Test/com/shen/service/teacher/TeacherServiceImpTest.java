package com.shen.service.teacher;

import com.google.gson.Gson;
import com.shen.model.user.Teacher;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeacherServiceImpTest {

    @Test
    public void getSchedule() {
        TeacherServiceImp teacherServiceImp = new TeacherServiceImp();
        String expected = "[{\"lessonId\":\"1\",\"classId\":\"2015221\",\"classroomId\":\"1419\",\"dday\":\"4\",\"dtime\":\"2\",\"courseName\":\"大型数据库管理\",\"teacherName\":\"杜海舟\"},{\"lessonId\":\"2\",\"classId\":\"2015221\",\"classroomId\":\"1419\",\"dday\":\"4\",\"dtime\":\"3\",\"courseName\":\"大型数据库管理\",\"teacherName\":\"杜海舟\"},{\"lessonId\":\"3\",\"classId\":\"2016221\",\"classroomId\":\"1419\",\"dday\":\"4\",\"dtime\":\"4\",\"courseName\":\"大型数据库管理\",\"teacherName\":\"杜海舟\"}]";

        Gson gson = new Gson();

        assertEquals(expected, gson.toJson(teacherServiceImp.getSchedule("100000")));
    }

    @Test
    public void getAttendence() {
        TeacherServiceImp teacherServiceImp = new TeacherServiceImp();
        String expected = "[{\"lessonId\":\"1\",\"classId\":\"2015221\",\"classroomId\":\"1419\",\"dday\":\"4\",\"dtime\":\"2\",\"courseName\":\"大型数据库管理\",\"teacherName\":\"杜海舟\"},{\"lessonId\":\"2\",\"classId\":\"2015221\",\"classroomId\":\"1419\",\"dday\":\"4\",\"dtime\":\"3\",\"courseName\":\"大型数据库管理\",\"teacherName\":\"杜海舟\"},{\"lessonId\":\"3\",\"classId\":\"2016221\",\"classroomId\":\"1419\",\"dday\":\"4\",\"dtime\":\"4\",\"courseName\":\"大型数据库管理\",\"teacherName\":\"杜海舟\"}]";

        Gson gson = new Gson();

        assertEquals(expected, gson.toJson(teacherServiceImp.getSchedule("100000")));
    }

    @Test
    public void getChartStatistics() {
        TeacherServiceImp teacherServiceImp = new TeacherServiceImp();
        String expected = "{\"lessonStatistices\":[{\"statisticsName\":\"1\",\"unattendenceCount\":\"14\"},{\"statisticsName\":\"2\",\"unattendenceCount\":\"9\"}],\"weekStatistices\":[{\"statisticsName\":\"1\",\"unattendenceCount\":\"15\"},{\"statisticsName\":\"2\",\"unattendenceCount\":\"8\"}]}";

        Gson gson = new Gson();

        assertEquals(expected, gson.toJson(teacherServiceImp.getChartStatistics("100000")));
    }

    @Test
    public void validate() {
        TeacherServiceImp teacherServiceImp = new TeacherServiceImp();


        assertEquals(1, teacherServiceImp.validate("100000", "12345"));
    }

    @Test
    public void getUser() {
        TeacherServiceImp teacherServiceImp = new TeacherServiceImp();
        Teacher teacher = new Teacher();
        teacher.setId("100000");
        teacher.setCollege("计算机学院");
        teacher.setName("杜海舟");
        teacher.setMajor("计算机科学与技术");
        teacher.setPassword("12345");

        Gson gson = new Gson();

        assertEquals(teacher.toString(), teacherServiceImp.getUser("100000", "12345").toString());
    }
}