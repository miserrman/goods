package com.ooad.good.service.impl;

import com.alibaba.fastjson.JSON;
import com.ooad.good.domain.CommentPo;
import com.ooad.good.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wz
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${comment-api}/admin/product/{id}/comments/in?userId={uid}&ip={ip}")
    String commentDeleteUrl;

    @Value("${comment-api}/admin/product/{id}/comments/in")
    String commentFindUrl;

    @Value("${comment-api}/admin/product/list/comments/in?productList={list}&userId={uid}&ip={ip}")
    String commentDeleteListUrl;

    @Override
    public void deleteCommentByProductId(Integer id, Integer userId, String ip) {
        commentDeleteUrl = commentDeleteUrl.replace("{uid}", userId.toString());
        commentDeleteUrl = commentDeleteUrl.replace("{ip}", ip);
        commentDeleteUrl = commentDeleteUrl.replace("{id}", id.toString());
        restTemplate.delete(commentDeleteUrl);
    }

    @Override
    public void deleteCommentByProductIdList(List<Integer> productList, Integer userId, String ip) {

        commentDeleteListUrl = commentDeleteListUrl.replace("{uid}", userId.toString());
        commentDeleteListUrl = commentDeleteListUrl.replace("{ip}", ip);
        String products = JSON.toJSONString(productList);
        commentDeleteListUrl = commentDeleteListUrl.replace("{list}", products);
        restTemplate.delete(commentDeleteListUrl);
    }

    public boolean validateComment(Integer productId) {
        return false;
    }
}
