package com.shen.service.admin;

import com.shen.model.user.Admin;
import com.shen.model.user.Student;
import com.shen.model.user.User;
import com.shen.mybatis.mappers.UserMapper;
import com.shen.mybatis.mappers.admin.AdminMapper;
import com.shen.mybatis.mappers.student.StudentMapper;
import com.shen.utils.ApplicationContextUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdminServiceImp implements AdminService {
    /**
     * 获取文件中，列对应用户类字段的列号
     * @param wb wb
     * @param userClass 用户类
     * @return 返回字段与列号的map
     */
    private Map<String, Integer> getColumnNameMap(XSSFWorkbook wb , Class<? extends User> userClass) {
        XSSFSheet sheet = wb.getSheetAt(0);

        Row row = sheet.getRow(0);

        Map<String, Integer> nameAndColumnNum = new HashMap<>();

        try{
            // 遍历单元格
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                // 获取列名
                String fieldName = row.getCell(i).getStringCellValue();
                // 查找字段名，将字段名与列号放入map中
                if(userClass.getDeclaredField(fieldName) != null){
                    nameAndColumnNum.put(fieldName,i);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return nameAndColumnNum;
    }

    /**
     * 将文件中的每行数据，插入数据库中
     * @param file 上传的execel文件
     * @param userClass 上传名单对应的用户类，可以为老师，学生
     * @param userMapper 用户类的map
     * @return 返回上传成成还是失败的字符串
     */
    private String fileUpload(MultipartFile file, Class<? extends User> userClass, UserMapper userMapper) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();

        try {
            // 转化excel文件
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            // 获取excel文件的第一面
            XSSFSheet sheet = wb.getSheetAt(0);

            // 获取字段与列号的映射
            Map<String, Integer> nameAndColumnNum = getColumnNameMap(wb, userClass);

            // 遍历每一行
            for (int k = 1; k < sheet.getPhysicalNumberOfRows(); k++) {
                XSSFRow row = sheet.getRow(k);

                // 创建user的实例
                User user = userClass.newInstance();

                // 获取文件中的列名集合
                Set<String> fieldNames = nameAndColumnNum.keySet();

                for (String fieldName : fieldNames) {
                    // 根据字段名获取字段
                    Field field = userClass.getDeclaredField(fieldName);

                    field.setAccessible(true);

                    // 获取字段对应的单元格
                    XSSFCell cell = row.getCell(nameAndColumnNum.get(fieldName));

                    // 根据单元格数据类型，将其转化成字符串设置到字段
                    switch (cell.getCellTypeEnum()){
                        case NUMERIC:
                            field.set(user, cell.getRawValue());
                            break;
                        case STRING:
                            field.set(user, String.valueOf(cell.getStringCellValue()));
                            break;
                    }
                }

                // 在数据库中插入字段
                userMapper.insertUser(user);
            }

            return "上传成功";
        } catch (IOException | IllegalAccessException | NoSuchFieldException | InstantiationException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    @Override
    public String studentFileUpload(MultipartFile file) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();

        return fileUpload(file, Student.class, applicationContext.getBean(StudentMapper.class));
    }

    @Override
    public List<Student> selectStudentInformation(String offset, String limit, String Id, String name, String classId, String sex, String major, String college) {
        RowBounds rowBounds = new RowBounds(Integer.valueOf(offset), Integer.valueOf(limit));
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);

        return studentMapper.selectRowBoundsStudent(rowBounds, Id, name, classId, sex, major, college);
    }

    @Override
    public int validate(String username, String password) {
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        AdminMapper adminMapper = applicationContext.getBean(AdminMapper.class);

        return adminMapper.validatePassword(username, password);
    }

    @Override
    public User getUser(String username, String password) {
        Admin admin = new Admin();
        admin.setId(username);

        return admin;
    }
}
