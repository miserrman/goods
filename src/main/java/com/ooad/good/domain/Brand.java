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
 * @Description:品牌对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Brand extends BrandPo {
    private List<GoodsPo> goodsPoList;

    public Brand(BrandPo brandPo) {
        this.setId(brandPo.getId());
        this.setName(brandPo.getName());
        this.setDescription(brandPo.getDescription());
        this.setPicUrl(brandPo.getPicUrl());
        this.setGmtCreate(brandPo.getGmtCreate());
        this.setGmtModified(brandPo.getGmtModified());
        this.setBeDeleted(brandPo.getBeDeleted());
    }

    public Brand() {

    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + this.getId() +
                ",name='" + this.getName() + '\'' +
                ",picUrl=" + this.getPicUrl() + '\'' +
                ",description=" + this.getDescription() + '\'' +
                ",gmtCreate=" + this.getGmtCreate() +
                ".gmtModified=" + this.getGmtModified() +
                ",beDeleted=" + this.getBeDeleted() +
                ",goodsPoList=" + goodsPoList +
                '}';
    }
}
