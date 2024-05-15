package com.cinema;

import com.controller.EmpController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class TestResetPassword {
    @Autowired
    private EmpController empController;

    @Test
    public void testReset() throws JsonProcessingException, UnsupportedEncodingException {
        String s = empController.applyResetPassword(7010, "juliechu311@gmail.com");
        System.out.println("接果 = " + s);
    }
}
