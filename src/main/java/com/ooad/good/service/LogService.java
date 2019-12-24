package com.ooad.good.service;

import com.ooad.good.util.Type;
import org.springframework.stereotype.Service;

@Service
public interface LogService {

    void writeAdminLog(Integer adminId, String ip, Type type, Integer actionId, String action, Integer statusCode);
}
