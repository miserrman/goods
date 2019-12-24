package com.ooad.good.service;

import com.ooad.good.domain.Brand;
import com.ooad.good.domain.BrandPo;
import com.ooad.good.util.exception.MallException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailAuthenticationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BrandServiceTest {

    @Autowired
    BrandService brandService;

    @Test
    void findAllBrands() throws MallException {
        List<Brand> brandList = brandService.findAllBrands(1, 3);
        System.out.println(brandList.size());
        System.out.println(brandList.get(1).getId());
        //System.out.println(brandList.get(2).getId());
        System.out.println(brandList.get(0).getId());
    }

    @Test
    void searchBrands() throws MallException {
        List<BrandPo> brandList = brandService.searchBrands(null, "戴", 1, 3);
        System.out.println(brandList.size());
        System.out.println(brandList.get(0).getId());
    }


    @Test
    void addOneBrand() throws MallException {
        BrandPo brandPo = new BrandPo();
        brandPo.setName("hhhTest");
        brandPo.setPicUrl("sdfskjfj");
        Brand brand = brandService.addOneBrand(brandPo);
        System.out.println(brand.getId());
    }

    @Test
    void findBrandById() throws MallException{
        Integer brandId = 5;
        BrandPo brandPo = brandService.findBrandById(brandId);
        System.out.println(brandPo.getName());
    }

    @Test
    void updateBrandById() throws MallException {
        Brand brand = new Brand();
        brand.setPicUrl("fjkdjfs");
        brand.setBeDeleted(false);
        brand.setDescription("sdkufhs");
        brand.setName("myTest");
        brandService.updateBrandById(3,brand);
    }
}
