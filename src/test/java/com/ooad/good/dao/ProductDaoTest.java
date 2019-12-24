package com.ooad.good.dao;

import com.ooad.good.domain.Product;
import com.ooad.good.util.exception.MallException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDaoTest {

    @Autowired
    ProductDao productDao;

    @Test
    void findProductById() throws MallException {
        Product product = productDao.findProductById(1);
        assertEquals(1,product.getGoodsId());
    }

    @Test
    void findProductByGoodsId() throws MallException{
        List<Product> productList = productDao.findProductByGoodsId(1,1,10);
        assertEquals(2,productList.size());
    }

    @Test
    void addGoodsProduct() throws MallException{
        Integer goodsId = 10;
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setPicUrl("add1");
        Product product2 = new Product();
        product2.setPicUrl("dsfsf");
        productList.add(product1);
        productList.add(product2);
        productDao.addGoodsProduct(goodsId,productList);
    }

    @Test
    void addProduct() throws MallException{
        Product product = new Product();
        product.setGoodsId(2);
        product.setPicUrl("add test!");
        BigDecimal bigDecimal = new BigDecimal("100");
        product.setPrice(bigDecimal);
        productDao.addProduct(product);
        assertEquals(500,product.getId());
    }

    @Test
    void addProductStock() throws MallException{
        Product product = new Product();
        product.setId(11);
        product.setPicUrl("add Stock Test");
        Integer stock = 100;
        productDao.addProductStock(product,stock);
        assertEquals(110,product.getSafetyStock());
    }

    @Test
    void updateProduct() throws MallException{
        Product product = new Product();
        product.setId(11);
        product.setPicUrl("update Stock Test");
        productDao.updateProduct(product.getId(),product);
        assertEquals(110,product.getSafetyStock());

    }

    @Test
    void clearProduct() throws MallException{
        boolean flag = productDao.clearProduct(11);
        assertEquals(true,flag);

    }

    @Test
    void vertifyProduct() throws MallException{
        Product product = new Product();
        product.setSafetyStock(111);
        boolean flag = productDao.vertifyProduct(product);
        assertEquals(false,flag);
    }

    @Test
    void consumeProductStock() throws MallException{
        Integer productId = 11;
        Integer num = 5;
        Product product = productDao.consumeProductStock(productId,num);
        assertEquals(110,product.getSafetyStock());
    }

    @Test
    void addProductStock1() throws MallException{
        Integer productId = 11;
        Integer num = 5;
        Product product = productDao.consumeProductStock(productId,num);
        assertEquals(11,product.getSafetyStock());
    }
}