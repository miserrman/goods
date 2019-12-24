package com.ooad.good.service.impl;

import com.ooad.good.domain.FootprintItem;
import com.ooad.good.domain.FootprintItemPo;
import com.ooad.good.domain.GoodsPo;
import com.ooad.good.service.FootPrintService;
import com.ooad.good.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class FootPrintServiceImpl implements FootPrintService {

    @Value("${footprint-api}/footprints?userId={uid}")
    private String footprintAddUrl;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void documentUserFootPrint(Integer userId, GoodsPo goodsPo){
        footprintAddUrl = footprintAddUrl.replace("{uid}", userId.toString());
        FootprintItemPo footprintItemPo = new FootprintItemPo();
        footprintItemPo.setUserId(userId);
        footprintItemPo.setGoodsId(goodsPo.getId());
        HttpEntity httpEntity = new HttpEntity(footprintItemPo);
        restTemplate.postForObject(footprintAddUrl, httpEntity, String.class);
    }
}
