package com.ooad.good.service;

import com.ooad.good.util.exception.MallException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wz
 */
public interface PictureService {

    /**
     * 获取图片url
     * @param file 文件
     * @return url url
     * @throws MallException 异常
     */
    String getPictureUrl(MultipartFile file) throws MallException;

}
