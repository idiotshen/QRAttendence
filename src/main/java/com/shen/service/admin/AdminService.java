package com.shen.service.admin;

import com.shen.model.user.Student;
import com.shen.service.BaseService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService extends BaseService {
    public String studentFileUpload(MultipartFile file);
    public List<Student> selectStudentInformation(String offset, String limit, String Id, String name, String classId, String sex, String major, String college);
}
