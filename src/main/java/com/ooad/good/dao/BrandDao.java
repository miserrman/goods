package com.ooad.good.dao;

import com.ooad.good.domain.Brand;
import com.ooad.good.domain.BrandPo;
import com.ooad.good.mapper.BrandMapper;
import com.ooad.good.util.ResponseCode;
import com.ooad.good.util.ResponseUtil;
import com.ooad.good.util.ReverseUtil;
import com.ooad.good.util.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BrandDao {

    @Autowired
    RedisDao redisDao;

    @Autowired
    BrandMapper brandMapper;

    public List<Brand> findAllBrands(Integer page, Integer limit) throws MallException{
       if ((page != null || limit != null) && (page <= 0 || limit <= 0)) {
           throw new MallException(ResponseCode.BAD_ARGUMENT);
       }
        List<Brand> resList = brandMapper.findAllBrands(page, limit);
        if (resList == null || resList.size() == 0) {
            throw new MallException(ResponseCode.GET_BRAND_LIST_FAIL);
        }
        return resList;
    }

    public Brand findBrandById(Integer brandId) throws MallException {
        Brand brand = (Brand) redisDao.readObjectFromRedis("B_" + brandId);
        if (brand != null) {
            return brand;
        }
        brand = brandMapper.findBrandById(brandId);
        if (brand == null) {
            throw new MallException(ResponseCode.BRAND_UNKNOWN);
        }
        redisDao.writeObjectToRedis("B_" + brandId, brand);
        return brand;
    }

    public Brand findBrandByName(String name){
        Brand brand = brandMapper.findBrandByName(name);
        return brand;
    }

    public boolean clearBrand(Integer brandId) throws MallException {
        redisDao.clearObjectFromRedis("B_" + brandId);
        Brand brand = this.findBrandById(brandId);
        brand.setBeDeleted(true);
        brand.setGmtModified(LocalDateTime.now());
        int res = brandMapper.updateBrand(brand);
        if(res <= 0) {
            throw new MallException(ResponseCode.BRAND_DELETE_ERROR);
        }
        else {
            return true;
        }
    }

    public Brand addBrand(Brand brand) throws MallException {
        brand.setGmtCreate(LocalDateTime.now());
        brand.setBeDeleted(false);
        int res = brandMapper.addBrand(brand);
        if(res <= 0) {
            throw new MallException(ResponseCode.BRAND_ADD_ERROR);
        }
        else {
            return brand;
        }
    }

    public Brand updateBrand(Integer brandId, Brand brand) throws MallException {
        brand.setBeDeleted(null);
        brand.setGmtCreate(null);
        brand.setGmtModified(null);
        if(brandId != null)
        {
            brand.setId(brandId);
            brand.setGmtModified(LocalDateTime.now());
        }
        int res = brandMapper.updateBrand(brand);
        if(res <= 0) {
            throw new MallException(ResponseCode.BRAND_UPDATE_ERROR);
        } else {
            brand = brandMapper.findBrandById(brandId);
            redisDao.updateObjectFromRedis("B_" + brandId, brand);
            return brand;
        }
    }

    public List<Brand> searchBrandByName(Integer brandId, String name, Integer page, Integer limit) throws MallException{
        if ((page != null || limit != null) && (page <= 0 || limit <= 0)) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<Brand> brandList = brandMapper.searchBrandByName(brandId, name, page, limit);
        if (brandList == null || brandList.size() == 0) {
            throw new MallException(ResponseCode.GET_BRAND_LIST_FAIL);
        }
        return brandList;
    }

}
