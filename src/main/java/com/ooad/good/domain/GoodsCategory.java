package com.ooad.good.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:二级目录对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class GoodsCategory extends GoodsCategoryPo implements Serializable {
    private List<GoodsPo> goodsPoList;

    public GoodsCategory(GoodsCategoryPo goodsCategoryPo) {
        this.setId(goodsCategoryPo.getId());
        this.setName(goodsCategoryPo.getName());
        this.setPicUrl(goodsCategoryPo.getPicUrl());
        this.setPid(goodsCategoryPo.getPid());
        this.setGmtCreate(goodsCategoryPo.getGmtCreate());
        this.setGmtModified(goodsCategoryPo.getGmtModified());
        this.setBeDeleted(goodsCategoryPo.getBeDeleted());
    }

    public GoodsCategory() {

    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "id=" + this.getId() +
                ",name='" + this.getName() + '\'' +
                ",picUrl='" + this.getPicUrl() + '\'' +
                ",pid=" + this.getPid() +
                ",gmtCreate=" + this.getGmtCreate() +
                ",gmtModified=" + this.getGmtModified() +
                ",beDeleted=" + this.getBeDeleted() +
                ",goodsPoList=" + goodsPoList +
                '}';
    }
}
