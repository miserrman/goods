package com.ooad.good.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author wz
 */
@Service
public interface CommentService {

    /**
     * 通过id删除品牌
     * @param id 品牌id
     * @param userId 用户id
     * @param ip ip地址
     */
    void deleteCommentByProductId(Integer id, Integer userId, String ip);

    /**
     * 通过id删除品牌列表
     * @param productList 货品列表
     * @param userId 用户id
     * @param ip ip地址
     */
    void deleteCommentByProductIdList(List<Integer> productList, Integer userId, String ip);

}
