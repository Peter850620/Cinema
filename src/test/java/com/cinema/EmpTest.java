package com.cinema;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmpTest {

    @Autowired
    EmpService empService;

    @Autowired
    JobService jobService;

    @Test
    public void testUpdateEmp(){

        EmpVO emp = empService.getbyId(7001);
        emp.setEmpName("香蕉");
        empService.updateEmp(emp);




    }

}
