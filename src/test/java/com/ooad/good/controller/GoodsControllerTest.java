package com.ooad.good.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodsControllerTest {


    @Autowired
    TestRestTemplate testRestTemplate;

    @Value("http://${host}:${port}/goods/{id}")
    private String findGoodUrl;


    @Test
    void addProductByGoodsId() {

    }

    @Test
    void listProductByGoodsId() {
    }

    @Test
    void updateProductById() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void addGoods() {
    }

    @Test
    void listGoods() {
    }

    @Test
    void getBrandGoods() throws Exception {
        URI uri = new URI(findGoodUrl.replace("{id}", "283"));
        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);

        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        // 发出HTTP请求
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void adminGetGoodsById() {
    }

    @Test
    void addStock() {
    }

    @Test
    void addProductByGoodsId1() {
    }

    @Test
    void listProductByGoodsId1() {
    }

    @Test
    void updateProductById1() {
    }

    @Test
    void deleteProductById1() {
    }


    @Test
    void addGoods1() {
    }

    @Test
    void listGoods1() {
    }

    @Test
    void getGoodsPo() {
    }

    @Test
    void getGoodsById1() {
    }

    @Test
    void adminGetGoodsById1() {
    }

    @Test
    void adminGetAllGoods() {
    }

    @Test
    void updateGoodsById() {
    }

    @Test
    void deleteGoodsById() {
    }

    @Test
    void getAllGoodsByCategoryId() {
    }

    @Test
    void listBrand() {
//        URI uri = new URI(findGoodUrl.replace("{id}", "283"));
//        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);
//
//        HttpEntity httpEntity = new HttpEntity(httpHeaders);
//
//        // 发出HTTP请求
//        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addBrand() {
    }

    @Test
    void getBrandById() {
    }

//    @Test
//    void getBrandGoods() {
//    }

    @Test
    void adminGetBrandGoods() {
    }

    @Test
    void updateBrandById() {
    }

    @Test
    void deleteBrandById() {
    }

    @Test
    void listGoodsCategory() {
    }

    @Test
    void addGoodsCategory() {
    }

    @Test
    void adminFindGoodsByGoodsCategoryId() {
    }

    @Test
    void getGoodsCategoryById() {
    }

    @Test
    void updateGoodsCategoryById() {
    }

    @Test
    void deleteGoodsCategory() {
    }

    @Test
    void listOneLevelGoodsCategory() {
    }

    @Test
    void listBrand1() {
    }

    @Test
    void listSecondLevelGoodsCategoryById() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductPoById() {
    }

    @Test
    void uploadImg() {
    }

    @Test
    void isOnSale() {
    }

    @Test
    void deductProduct() {
    }

    @Test
    void addProductStock() {
    }
}
