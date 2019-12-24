package com.ooad.good.service;

import com.ooad.good.util.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogServiceTest {

    @Autowired
    LogService logService;

    @Test
    void writeAdminLog() {
        logService.writeAdminLog(12, "csd.cds", Type.INSERT, 12, "what is your name", 2);
    }
}
