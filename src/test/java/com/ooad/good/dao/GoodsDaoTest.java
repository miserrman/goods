package com.ooad.good.dao;

import com.ooad.good.domain.Goods;
import com.ooad.good.util.exception.MallException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodsDaoTest {

    @Autowired
    GoodsDao goodsDao;

    @Test
    void searchGoodsByName() throws MallException {
        List<Goods> goods = goodsDao.searchGoodsByName("平安音福",1,10);
        assertEquals(4,goods.size());
    }

    @Test
    void findGoodsById()  throws MallException{
        Goods goods = goodsDao.findGoodsById(680);
        assertEquals("农夫山泉",goods.getName());
    }

    @Test
    void findAllGoods()  throws MallException{
        List<Goods> goodsList = goodsDao.findAllGoods(1,100);
        assertEquals(400,goodsList.size());
    }

    @Test
    void findGoodsByBrandId()  throws MallException{
        List<Goods> goodsList = goodsDao.findGoodsByBrandId(104,1,100,4);
        assertEquals(3,goodsList.size());
    }

    @Test
    void findGoodsByGoodCategoryId()  throws MallException{
        List<Goods> goodsList = goodsDao.findGoodsByGoodCategoryId(126,1,100,4);
        assertEquals(5,goodsList.size());
    }

    @Test
    void searchGoods()  throws MallException{
        List<Goods> goodsList = goodsDao.searchGoods("平安金福",null,1,10);
        assertEquals(4,goodsList.size());
    }

    @Test
    void findGoodsSelling()  throws MallException{
        List<Goods> goodsList = goodsDao.findAllGoods(1,100);
        assertEquals(400,goodsList.size());
    }

    @Test
    void addGoods()  throws MallException{
        Goods goods = new Goods();
        BigDecimal price = new BigDecimal("999.99");
        goods.setPrice(price);
        goods.setBrandId(72);
        goods.setName("wz");
        goodsDao.addGoods(goods);
        assertEquals(681,goods.getId());
    }

    @Test
    void updateGoods()  throws MallException{
        Goods goods = new Goods();
        goods.setName("update Success!");
        goodsDao.updateGoods(681,goods,1);
        assertEquals(72,goods.getBrandId());
    }

    @Test
    void clearGoods()  throws MallException{
        boolean flag = goodsDao.clearGoods(681);
        assertEquals(true,flag);
    }

    @Test
    void deletedBrandGoodsUpdate()  throws MallException{
        boolean flag = goodsDao.deletedBrandGoodsUpdate(104);
        assertEquals(true,flag);
    }

    @Test
    void deletedGoodsCategoryGoodsUpdate()  throws MallException{
        boolean flag = goodsDao.deletedGoodsCategoryGoodsUpdate(126);
        assertEquals(true,flag);
    }
}