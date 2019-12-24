package com.ooad.good.mapper;

import com.ooad.good.domain.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class BrandMapperTest {

    @Autowired
    BrandMapper brandMapper;

    @Test
    void findBrandById() {
        Brand brand = brandMapper.findBrandById(107);
        System.out.println(brand);
    }

    @Test
    void findBrandByName() {
        Brand brand = brandMapper.findBrandByName("戴");
    }

    @Test
    void findAllBrands() {
    }

    @Test
    void updateBrand() {
    }

    @Test
    void addBrand() {
    }

    @Test
    void searchBrandByName() {
    }
}
