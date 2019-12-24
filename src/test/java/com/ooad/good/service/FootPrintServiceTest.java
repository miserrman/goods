package com.ooad.good.service;

import com.netflix.discovery.converters.Auto;
import com.ooad.good.domain.GoodsPo;
import com.ooad.good.util.ResponseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FootPrintServiceTest {

    @Autowired
    FootPrintService footPrintService;

    @Test
    void documentUserFootPrint() {
        GoodsPo goodsPo = new GoodsPo();
        goodsPo.setId(1);
        goodsPo.setBrandId(2);
        goodsPo.setGoodsCategoryId(4);

    }
}
