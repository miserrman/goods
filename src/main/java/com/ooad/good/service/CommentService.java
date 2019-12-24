package com.ooad.good.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public interface CommentService {

    void deleteCommentByProductId(Integer id, Integer userId, String ip);

    void deleteCommentByProductIdList(List<Integer> productList, Integer userId, String ip);

}
