package com.ooad.good.mapper;

import com.ooad.good.domain.GoodsCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class GoodsCategoryMapperTest {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Test
    void findGoodsCategory() {
        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.findGoodsCategory(1, 5);
        for(GoodsCategory goodsCategory : goodsCategoryList)
            System.out.println(goodsCategory);
    }

    @Test
    void findGoodsCategoryById() {
    }

    @Test
    void addGoodsCategory() {
    }

    @Test
    void updateGoodsCategory() {
    }

    @Test
    void findLevelOneGoodsCategory() {
    }

    @Test
    void findLevelTwoGoodsCategory() {
    }

    @Test
    void findLevelTwoGoodsCategoryByLevelOneId() {
    }
}
