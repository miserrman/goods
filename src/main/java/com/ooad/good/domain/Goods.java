package com.ooad.good.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:商品对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Goods extends GoodsPo {
    private BrandPo brandPo;
    private GoodsCategoryPo goodsCategoryPo;
    private List<ProductPo> productPoList;
    private GrouponRule grouponRule;
    private ShareRule shareRule;
    private PresaleRule presaleRule;
    @JsonIgnore
    public Goods(GoodsPo goodsPo) {
        this.setId(goodsPo.getId());
        this.setName(goodsPo.getName());
        this.setGoodsSn(goodsPo.getGoodsSn());
        this.setShortName(goodsPo.getShortName());
        this.setDescription(goodsPo.getDescription());
        this.setBrief(goodsPo.getBrief());
        this.setPicUrl(goodsPo.getPicUrl());
        this.setDetail(goodsPo.getDetail());
        this.setStatusCode(goodsPo.getStatusCode());
        this.setShareUrl(goodsPo.getShareUrl());
        this.setGallery(goodsPo.getGallery());
        this.setGoodsCategoryId(goodsPo.getGoodsCategoryId());
        this.setBrandId(goodsPo.getBrandId());
        this.setWeight(goodsPo.getWeight());
        this.setVolume(goodsPo.getVolume());
        this.setSpecialFreightId(goodsPo.getSpecialFreightId());
        this.setBeSpecial(goodsPo.getBeSpecial());
        this.setPrice(goodsPo.getPrice());
        this.setBeDeleted(goodsPo.getBeDeleted());
        this.setGmtCreate(goodsPo.getGmtCreate());
        this.setGmtModified(goodsPo.getGmtModified());
    }

    public Goods() {

    }


    @Override
    public String toString() {
        return "Goods{" +
                "id=" + this.getId() +
                ",name='" + this.getName() + '\'' +
                ",statusCode=" + this.getStatusCode() +
                ",brandId=" + this.getBrandId() +
                ",goodsCategoryId=" + this.getGoodsCategoryId() +
                ",brief='" + this.getBrief() + '\'' +
                ",description='" + this.getDescription() + '\'' +
                ",detail='" + this.getDetail() + '\'' +
                ",gallery='" + this.getGallery() + '\'' +
                ",goodsSn='" + this.getGoodsSn() + '\'' +
                ",picUrl='" + this.getPicUrl() +  '\'' +
                ",weight=" + this.getWeight() +
                ",volume=" + this.getVolume() +
                ",shareUrl='" + this.getShareUrl() + '\'' +
                ",shortName='" + this.getShortName() + '\'' +
                ",beSpecial=" + this.getBeSpecial()  +
                ",specialFreightId=" + this.getSpecialFreightId() +
                ",brandPo=" + brandPo +
                ",goodsCategoryPo=" + goodsCategoryPo +
                ",productPoList=" + productPoList +
                ",grouponRule=" + grouponRule +
                ",shareRule=" + shareRule +
                ",presaleRule=" + presaleRule +
                '}';
    }
}
