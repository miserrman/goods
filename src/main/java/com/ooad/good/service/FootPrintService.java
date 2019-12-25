package com.ooad.good.service;

import com.ooad.good.domain.GoodsPo;
import org.springframework.stereotype.Service;

/**
 * @author wz
 */
@Service
public interface FootPrintService {

    /**
     * 生成足迹
     * @param userId 用户id
     * @param goodsPo 商品po
     */
    void documentUserFootPrint(Integer userId, GoodsPo goodsPo);

}
