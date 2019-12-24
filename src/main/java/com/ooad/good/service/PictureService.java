package com.ooad.good.service;

import com.ooad.good.util.exception.MallException;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

    String getPictureUrl(MultipartFile file) throws MallException;

}
