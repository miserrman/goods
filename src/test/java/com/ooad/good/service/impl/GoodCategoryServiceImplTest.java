package com.ooad.good.service.impl;

import com.netflix.discovery.converters.Auto;
import com.ooad.good.domain.Goods;
import com.ooad.good.domain.GoodsCategory;
import com.ooad.good.domain.GoodsCategoryPo;
import com.ooad.good.domain.GoodsPo;
import com.ooad.good.service.GoodCategoryService;
import com.ooad.good.util.exception.MallException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodCategoryServiceImplTest {

    @Autowired
    GoodCategoryService goodCategoryService;

    @Test
    void findAllCategories() throws MallException {
        List<GoodsCategoryPo> goodsCategoryPos = goodCategoryService.findAllCategories(1, 4);
        for(GoodsCategoryPo goodsCategoryPo : goodsCategoryPos) {
            System.out.println(goodsCategoryPo);
        }
    }

    @Test
    void findAllSecondRankCategory() throws MallException{
        List<GoodsCategoryPo> goodsCategoryPos = goodCategoryService.findAllSecondRankCategory(2, 2);
        for(GoodsCategoryPo goodsCategoryPo : goodsCategoryPos) {
            System.out.println(goodsCategoryPo);
        }
    }

    @Test
    void findSecondRankCategoryByParent() throws MallException {
        List<GoodsCategoryPo> goodsCategoryPos = goodCategoryService.findSecondRankCategoryByParent(10, 1, 8);
        for (GoodsCategoryPo goodsCategoryPo : goodsCategoryPos) {
            System.out.println(goodsCategoryPo);
        }
    }

    @Test
    void addGoodCategory() throws MallException {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName("乔瑟夫乔巴那");
        goodCategoryService.addGoodCategory(goodsCategory);
    }

    @Test
    void getGoodsCategoryById() throws MallException {
        GoodsCategory goodsCategory = goodCategoryService.getGoodsCategoryById(3);
        System.out.println(goodsCategory.getId());
    }

    @Test
    void updateGoodsCategory() throws MallException {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName("木大木大");
        goodCategoryService.updateGoodsCategory(3, goodsCategory);
    }

    @Test
    void clearGoodCategory() throws MallException {
        goodCategoryService.clearGoodCategory(2);
    }

    @Test
    void findRankOneGoodsCategory() {
    }

    @Test
    void findAllCategories1() {
    }

    @Test
    void findAllSecondRankCategory1() {
    }

    @Test
    void findSecondRankCategoryByParent1() {
    }

    @Test
    void addGoodCategory1() {
    }

    @Test
    void getGoodsCategoryById1() {
    }

    @Test
    void updateGoodsCategory1() {
    }

    @Test
    void clearGoodCategory1() {
    }

    @Test
    void findRankOneGoodsCategory1() {
    }

    @Test
    void updateGoodsCategoryPid() throws MallException {
        Integer id = 122;
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setPid(134);
        goodCategoryService.updateGoodsCategoryPid(id, goodsCategory);
    }
}
