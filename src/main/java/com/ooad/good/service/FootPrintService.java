package com.ooad.good.service;

import com.ooad.good.domain.GoodsPo;
import org.springframework.stereotype.Service;

@Service
public interface FootPrintService {

    void documentUserFootPrint(Integer userId, GoodsPo goodsPo);

}
