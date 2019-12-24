package com.ooad.good.util;

import com.ooad.good.domain.*;

public class ReverseUtil {

    public static ProductPo reverseToProductPo(Product product) {
        ProductPo productPo = new ProductPo();
        productPo.setId(product.getId());
        productPo.setGoodsId(product.getGoodsId());
        productPo.setPicUrl(product.getPicUrl());
        productPo.setSpecifications(product.getSpecifications());
        productPo.setSafetyStock(product.getSafetyStock());
        productPo.setPrice(product.getPrice());
        productPo.setGmtCreate(product.getGmtCreate());
        productPo.setGmtModified(product.getGmtModified());
        productPo.setBeDeleted(product.getBeDeleted());
        return productPo;
    }

    public static GoodsPo reverseToGoodsPo(Goods goods) {
        GoodsPo goodsPo = new GoodsPo();
        goodsPo.setId(goods.getId());
        goodsPo.setName(goods.getName());
        goodsPo.setGoodsSn(goods.getGoodsSn());
        goodsPo.setShortName(goods.getShortName());
        goodsPo.setDescription(goods.getDescription());
        goodsPo.setBrief(goods.getBrief());
        goodsPo.setPicUrl(goods.getPicUrl());
        goodsPo.setDetail(goods.getDetail());
        goodsPo.setStatusCode(goods.getStatusCode());
        goodsPo.setShareUrl(goods.getShareUrl());
        goodsPo.setGallery(goods.getGallery());
        goodsPo.setGoodsCategoryId(goods.getGoodsCategoryId());
        goodsPo.setBrandId(goods.getBrandId());
        goodsPo.setWeight(goods.getWeight());
        goodsPo.setVolume(goods.getVolume());
        goodsPo.setSpecialFreightId(goods.getSpecialFreightId());
        goodsPo.setBeSpecial(goods.getBeSpecial());
        goodsPo.setPrice(goods.getPrice());
        goodsPo.setBeDeleted(goods.getBeDeleted());
        goodsPo.setGmtCreate(goods.getGmtCreate());
        goodsPo.setGmtModified(goods.getGmtModified());
        return goodsPo;
    }

    public static BrandPo reverseToBrandPo(Brand brand) {
        BrandPo brandPo = new BrandPo();
        brandPo.setId(brand.getId());
        brandPo.setName(brand.getName());
        brandPo.setPicUrl(brand.getPicUrl());
        brandPo.setDescription(brand.getDescription());
        brandPo.setBeDeleted(brand.getBeDeleted());
        brandPo.setGmtCreate(brand.getGmtCreate());
        brandPo.setGmtModified(brand.getGmtModified());
        return brandPo;
    }

    public static GoodsCategoryPo reverseToGoodsCategory(GoodsCategory goodsCategory) {
        GoodsCategoryPo goodsCategoryPo = new GoodsCategoryPo();
        goodsCategoryPo.setId(goodsCategory.getId());
        goodsCategoryPo.setName(goodsCategory.getName());
        goodsCategoryPo.setPicUrl(goodsCategory.getPicUrl());
        goodsCategoryPo.setPid(goodsCategory.getPid());
        goodsCategoryPo.setBeDeleted(goodsCategory.getBeDeleted());
        goodsCategoryPo.setGmtCreate(goodsCategory.getGmtCreate());
        goodsCategoryPo.setGmtModified(goodsCategory.getGmtModified());
        return goodsCategoryPo;
    }
}
