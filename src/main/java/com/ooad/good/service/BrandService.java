package com.ooad.good.service;

import com.ooad.good.domain.Brand;
import com.ooad.good.domain.BrandPo;
import com.ooad.good.util.exception.MallException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wz
 */
@Service
public interface BrandService {

    /**
     * 得到所有品牌
     * @param page 页数
     * @param limit 限制
     * @return 所有品牌
     * @throws MallException 异常
     */
    List<Brand> findAllBrands(Integer page, Integer limit) throws MallException;

    /**
     * 通过条件查询品牌
     * @param brandId 品牌id
     * @param name 品牌名
     * @param page 页数
     * @param limit 页面限制限制
     * @throws MallException 异常
     * @return List<BrandPo> 品牌列表
     */
    List<BrandPo> searchBrands(Integer brandId, String name, Integer page, Integer limit) throws MallException;
    /**
     * 添加品牌
     * @param brandPo 品牌po
     * @return brand 品牌
     * @throws MallException 异常
     */
    Brand addOneBrand(BrandPo brandPo) throws MallException;

    /**
     * 通过id查找品牌
     * @param id 品牌id
     * @return 返回品牌
     * @throws MallException 异常
     */
    Brand findBrandById(Integer id) throws MallException;

    /**
     * 通过品牌id更新品牌
     * @param id 品牌id
     * @param brand 品牌
     * @throws MallException 异常
     * @return brand 品牌
     */
    Brand updateBrandById(Integer id, Brand brand) throws MallException;

    /**
     * 通过品牌id删除品牌
     * @param id 品牌id
     * @return boolean 状态
     * @throws MallException 异常
     */
    boolean clearBrandById(Integer id) throws MallException;
}
