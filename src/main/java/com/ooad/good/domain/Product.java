package com.ooad.good.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:产品对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Product extends ProductPo {
    private Goods goods;

    public Product(ProductPo productPo) {
        this.setId(productPo.getId());
        this.setPrice(productPo.getPrice());
        this.setPicUrl(productPo.getPicUrl());
        this.setSafetyStock(productPo.getSafetyStock());
        this.setBeDeleted(productPo.getBeDeleted());
        this.setSpecifications(productPo.getSpecifications());
        this.setGmtCreate(productPo.getGmtCreate());
        this.setGmtModified(productPo.getGmtModified());
        this.setGoodsId(productPo.getGoodsId());

    }

    public Product() {

    }
}
