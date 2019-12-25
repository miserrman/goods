package com.ooad.good.service.impl;

import com.ooad.good.dao.BrandDao;
import com.ooad.good.dao.GoodsDao;
import com.ooad.good.domain.Brand;
import com.ooad.good.domain.BrandPo;
import com.ooad.good.domain.GoodsPo;
import com.ooad.good.service.BrandService;
import com.ooad.good.util.ReverseUtil;
import com.ooad.good.util.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wz
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandDao brandDao;

    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<Brand> findAllBrands(Integer page, Integer limit) throws MallException {
        List<Brand> resList = brandDao.findAllBrands(page, limit);
        return resList;
    }

    @Override
    public List<BrandPo> searchBrands(Integer brandId, String name, Integer page, Integer limit) throws MallException {
        List<Brand> resList = brandDao.searchBrandByName(brandId, name, page, limit);
        List<BrandPo> brandPos = resList.stream().map(ReverseUtil::reverseToBrandPo).collect(Collectors.toList());
        return brandPos;
    }

    @Override
    public Brand addOneBrand(BrandPo brandPo) throws MallException {
        Brand brand = null;
        brand = new Brand(brandPo);
        Brand b = brandDao.addBrand(brand);
        return b;
    }

    @Override
    public Brand findBrandById(Integer id) throws MallException {
        Brand brand = brandDao.findBrandById(id);
        return brand;
    }

    @Override
    public Brand updateBrandById(Integer id, Brand brand) throws MallException {
        brandDao.findBrandById(id);
        Brand res = brandDao.updateBrand(id, brand);
        return res;
    }

    @Override
    public boolean clearBrandById(Integer id) throws MallException {
        boolean res = brandDao.clearBrand(id);
        goodsDao.deletedBrandGoodsUpdate(id);

        return res;
    }

}
