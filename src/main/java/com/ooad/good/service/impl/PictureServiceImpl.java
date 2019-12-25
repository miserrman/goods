package com.ooad.good.service.impl;

import com.ooad.good.service.PictureService;
import com.ooad.good.util.ResponseCode;
import com.ooad.good.util.ResponseUtil;
import com.ooad.good.util.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author wz
 */
@Service
public class PictureServiceImpl implements PictureService {

    private static String pictureLoadPath = "/home/ooad_project/picture/";

    private static String returnUrl = "http;//47.103.29.203/static/picture/";
    @Override
    public String getPictureUrl(MultipartFile file) throws MallException {
        String fileName = file.getName();
        File f = new File(pictureLoadPath + fileName);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            throw new MallException(ResponseCode.UNDER_SHELF_ERROR);
        }
        return returnUrl + fileName;
    }
}
