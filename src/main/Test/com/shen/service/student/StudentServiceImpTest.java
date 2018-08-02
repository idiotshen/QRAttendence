package com.shen.service.student;

import com.google.gson.Gson;
import com.shen.model.user.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentServiceImpTest {
    @Test
    public void getSchedule() {
        StudentServiceImp studentServiceImp = new StudentServiceImp();

        String expResult = "[{\"lessonId\":\"1\",\"classId\":\"2015221\",\"classroomId\":\"1419\",\"dday\":\"4\",\"dtime\":\"2\",\"courseName\":\"大型数据库管理\",\"teacherName\":\"杜海舟\"},{\"lessonId\":\"2\",\"classId\":\"2015221\",\"classroomId\":\"1419\",\"dday\":\"4\",\"dtime\":\"3\",\"courseName\":\"大型数据库管理\",\"teacherName\":\"杜海舟\"}]";

        Gson gson = new Gson();
        String result = gson.toJson(studentServiceImp.getSchedule("2015221"));

        assertEquals(expResult, result);
    }

    @Test
    public void getAttendence() {
        Gson gson = new Gson();

        StudentServiceImp studentServiceImp = new StudentServiceImp();

        String expResult = "{\"classroomIds\":[\"1419\"],\"weeks\":[\"1\",\"2\"],\"unattendences\":[{\"studentId\":\"20151897\",\"lessonId\":\"1\",\"dday\":\"4\",\"classroomId\":\"1419\",\"dtime\":\"2\",\"week\":1},{\"studentId\":\"20151897\",\"lessonId\":\"1\",\"dday\":\"4\",\"classroomId\":\"1419\",\"dtime\":\"2\",\"week\":2},{\"studentId\":\"20151897\",\"lessonId\":\"2\",\"dday\":\"4\",\"classroomId\":\"1419\",\"dtime\":\"3\",\"week\":1}],\"unattendenceStatistices\":[{\"dday\":\"4\",\"dtime\":\"2\",\"count\":\"2\"},{\"dday\":\"4\",\"dtime\":\"3\",\"count\":\"1\"}],\"dtimes\":[\"2\",\"3\"],\"ddays\":[\"4\"],\"lessonIds\":[\"1\",\"2\"]}";

        String result = gson.toJson(studentServiceImp.getAttendence("20151897"));

        assertEquals(expResult, result);
    }

    @Test
    public void getChartStatistics() {
        Gson gson = new Gson();

        StudentServiceImp studentServiceImp = new StudentServiceImp();

        String expResult = "{\"weekStatistices\":[{\"statisticsName\":\"1\",\"unattendenceCount\":\"2\"},{\"statisticsName\":\"2\",\"unattendenceCount\":\"1\"}]}";

        String result = gson.toJson(studentServiceImp.getChartStatistics("20151897"));

        assertEquals(expResult, result);
    }

    @Test
    public void validate() {
        StudentServiceImp studentServiceImp = new StudentServiceImp();
        int expected = studentServiceImp.validate("20151897", "12345");

        int expResult = 1;

        assertEquals(expResult, expected);
    }

    @Test
    public void getUser() {
        StudentServiceImp studentServiceImp = new StudentServiceImp();

        Student student = new Student();
        student.setName("沈嘉伟");
        student.setId("20151892");
        student.setClassId("2015221");
        student.setCollege("计算机学院");
        student.setMajor("软件工程");
        student.setSex("男");
        student.setPassword("12345");

        assertEquals(student.toString(), studentServiceImp.getUser("20151892", "12345").toString());
    }
}