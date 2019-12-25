package com.ooad.good.service;

import com.ooad.good.util.Type;
import org.springframework.stereotype.Service;

/**
 * @author wz
 */
@Service
public interface LogService {

    /**
     * 写日志
     * @param adminId 管理员id
     * @param ip ip地址
     * @param type 操作类型
     * @param actionId 操作id
     * @param action 操作
     * @param statusCode 操作状态
     */
    void writeAdminLog(Integer adminId, String ip, Type type, Integer actionId, String action, Integer statusCode);
}
