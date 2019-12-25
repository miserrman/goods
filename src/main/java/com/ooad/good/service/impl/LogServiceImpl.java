package com.ooad.good.service.impl;

import com.ooad.good.domain.Log;
import com.ooad.good.service.LogService;
import com.ooad.good.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author wz
 */
@Service
public class LogServiceImpl implements LogService {

    @Value("http://101.37.30.223:8081/logs")
    String logUrl;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void writeAdminLog(Integer adminId, String ip, Type type, Integer actionId, String action, Integer statusCode) {
        Log log = new Log();
        if (adminId == null || adminId == -1) {
            log.setAdminId(null);
        }
        else {
            log.setAdminId(adminId);
        }
        log.setAdminId(adminId);
        log.setIp(ip);
        log.setType(type.getIndex());
        log.setActions(action);
        log.setStatusCode(statusCode);
        log.setActionId(actionId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(logUrl, log, String.class);
    }
}
