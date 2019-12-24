package com.ooad.good.service;

import com.ooad.good.domain.Goods;
import com.ooad.good.domain.GoodsPo;
import com.ooad.good.domain.Product;
import com.ooad.good.domain.ProductPo;
import com.ooad.good.util.exception.MallException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @Test
    void userFindGoodsByCondition() {

    }

    @Test
    void findAllGoods() throws MallException {
        List<GoodsPo> goodsPoList = goodsService.findAllGoods(1, 5);
        for(GoodsPo goodsPo : goodsPoList) {
            System.out.println(goodsPo);
        }
    }

    @Test
    void updateGoodsById() throws MallException {
        Goods goods = new Goods();
        goods.setBrandId(105);
        goods.setGoodsCategoryId(125);
        GoodsPo goodsPo = goodsService.updateGoodsById(277, goods);
        System.out.println(goodsPo.getId());
    }

    @Test
    void clearGoodsById() throws MallException {

    }

    @Test
    void addGoods() throws MallException {
        Goods goods = new Goods();
        goods.setName("sdcsdcsdc");
        goodsService.addGoods(goods);
    }

    @Test
    void findGoods() throws MallException {
        Goods goods = goodsService.findGoods(7, 1, 4);
        System.out.println(goods);
    }

    @Test
    void enterGoodsDetail() {
    }

    @Test
    void sellingGoods() {
    }

    @Test
    void searchGoodsByCondition() throws MallException {
        List<GoodsPo> goodsPoList = goodsService.searchGoodsByCondition(null, "瓷瓶",1, 10);
        for(GoodsPo goodsPo : goodsPoList) {
            System.out.println(goodsPo);
        }
    }

    @Test
    void addGoodsProducts() throws MallException {
        Product product = new Product();
        product.setPicUrl("http://324.23423..432.34");
        product.setSafetyStock(3444);
        product.setPrice(new BigDecimal(234));
        goodsService.addGoodsProducts(670, product);
        //错误
    }

    @Test
    void findGoodsProduct() throws MallException {
        List<ProductPo> productPoList = goodsService.findGoodsProduct(663, 1, 5);
        for(ProductPo productPo : productPoList) {
            System.out.println(productPo);
        }
    }

    @Test
    void updateProduct() throws MallException {
        Product product = new Product();
        product.setId(5);
        product.setPicUrl("http://localhost/8081");
        goodsService.updateProduct(5, product);
    }

    @Test
    void clearProduct() throws MallException {

    }

    @Test
    void findBrandsAllGoods()  throws MallException{
        List<GoodsPo> goodsPoList = goodsService.findBrandsAllGoods(112, 1, 2, 1);
        System.out.println(goodsPoList.size());
    }

    @Test
    void findGoodsCategoriesAllGoods() throws  MallException{
        List<GoodsPo> goodsPoList = goodsService.findGoodsCategoriesAllGoods(128, 1, 10, 1);
        System.out.println(goodsPoList.size());
        for (GoodsPo goodsPo : goodsPoList) {
            System.out.println(goodsPo);
        }
    }

    @Test
    void findProduct() throws MallException {
        Product product = goodsService.findProduct(523);
        System.out.println(product);
    }

    @Test
    void addProduct() throws MallException {
        Product product = new Product();
        product.setId(3);
        product.setGoodsId(8);
        product.setSafetyStock(50);
        goodsService.addProduct(product);
    }

    @Test
    void updateListProduct() {
        HashMap<Integer, Integer> hashMap = new HashMap();
//        hashMap.put()
//        goodsService.updateListProduct();
    }

    @Test
    void searchGoodsNameTest() throws MallException {
        List<GoodsPo> goodsPoList = goodsService.searchGoodsByName("金和汇", 1, 30);
        for (GoodsPo goodsPo : goodsPoList)
            System.out.println(goodsPo);
    }

    @Test
    void searchGoods() throws MallException {
        List<GoodsPo> goodsPoList = goodsService.searchGoodsByCondition("lj-d0001", "金和汇景•李进•粉彩仕女图瓷板", 1, 2);
        for(GoodsPo goodsPo : goodsPoList)
            System.out.println(goodsPo);
    }
}
