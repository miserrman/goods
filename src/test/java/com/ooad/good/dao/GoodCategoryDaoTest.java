package com.ooad.good.dao;

import com.ooad.good.domain.GoodsCategory;
import com.ooad.good.util.exception.MallException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodCategoryDaoTest {

    @Autowired
    GoodCategoryDao goodCategoryDao;

    @Test
    void findAllCategories() throws MallException {
        List<GoodsCategory> categoryList = goodCategoryDao.findAllCategories(1,100);
        assertEquals(14,categoryList.size());

    }

    @Test
    void findRankOneCategories() throws MallException {
        List<GoodsCategory> goodsCategories = goodCategoryDao.findRankOneCategories(1,100);
        assertEquals(2,goodsCategories.size());
    }

    @Test
    void findRankTwoCategories() throws MallException {
        List<GoodsCategory> goodsCategories = goodCategoryDao.findRankOneCategories(1,100);
        assertEquals(12,goodsCategories.size());
    }

    @Test
    void findRankTwoCategoriesByParent() throws MallException {
        List<GoodsCategory> goodsCategories = goodCategoryDao.findRankTwoCategoriesByParent(127,1,10);
        assertEquals(8,goodsCategories.size());
    }

    @Test
    void findGoodCategoryById() throws MallException {
        GoodsCategory goodsCategory = goodCategoryDao.findGoodCategoryById(125);
        assertEquals("收藏品",goodsCategory.getName());
    }

    @Test
    void addGoodCategory() throws MallException {
        GoodsCategory goodsCategory =  new GoodsCategory();
        goodsCategory.setName("wz");
        goodsCategory.setPicUrl("hhhhh");
        goodCategoryDao.addGoodCategory(goodsCategory);
        ;
    }

    @Test
    void updateGoodCategory() throws MallException {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setPicUrl("update Success!");
        goodCategoryDao.updateGoodCategory(140,goodsCategory);
        assertEquals("wz",goodsCategory.getName());
    }

    @Test
    void clearGoodCategory() throws MallException {
        boolean flag = goodCategoryDao.clearGoodCategory(131);
        assertEquals(true,flag);
    }
}