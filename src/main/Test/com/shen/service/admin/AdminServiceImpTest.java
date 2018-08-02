package com.shen.service.admin;

import com.shen.model.user.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminServiceImpTest {
    @Test
    public void selectStudentInformation() {
        AdminServiceImp adminServiceImp = new AdminServiceImp();
        adminServiceImp.selectStudentInformation("0", "10", "20151892","","","","","");
    }

    @Test
    public void validate() {
        AdminServiceImp adminServiceImp = new AdminServiceImp();

        assertEquals(1, adminServiceImp.validate("admin", "12345"));
    }

    @Test
    public void getUser() {
        AdminServiceImp adminServiceImp = new AdminServiceImp();

        Admin admin = new Admin();

        admin.setId("admin");

        assertEquals(admin.toString(), adminServiceImp.getUser("admin","12345").toString());
    }
}