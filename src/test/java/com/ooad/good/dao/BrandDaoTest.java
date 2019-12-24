package com.ooad.good.dao;

import com.ooad.good.domain.Brand;
import com.ooad.good.util.exception.MallException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BrandDaoTest {

    @Autowired
    BrandDao brandDao;

    @Test
    void findAllBrands() throws MallException {
        List<Brand> brandList = brandDao.findAllBrands(1,100);
        assertEquals(49,brandList.size());
    }

    @Test
    void findBrandById() throws MallException{
        Brand brand = brandDao.findBrandById(72);
        assertEquals("范敏祺",brand.getName());
    }

    @Test
    void findBrandByName() throws MallException{
        Brand brand = brandDao.findBrandByName("俞军");
        assertEquals(84,brand.getId());
    }

    @Test
    void clearBrand() throws MallException{
        Brand brand = brandDao.findBrandById(74);
        assertEquals("李进",brand.getName());
        boolean flag = brandDao.clearBrand(74);
        assertEquals(true,flag);
    }

    @Test
    void addBrand() throws MallException{
        Brand brand = new Brand();
        brand.setName("wz");
        brand.setDescription("hhhh");
        brandDao.addBrand(brand);
        assertEquals(120,brand.getId());
    }

    @Test
    void updateBrand() throws MallException{
        Brand brand = new Brand();
        brand.setId(120);
        brand.setDescription("update Success!");
        Brand temp = brandDao.updateBrand(120,brand);
        assertEquals("wz",temp.getName());

    }

    @Test
    void searchBrandByName() throws MallException{
        List<Brand> brandList = brandDao.searchBrandByName(null,"wz",1,10);
        System.out.println(brandList);
    }
}