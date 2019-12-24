package com.ooad.good.service;

import com.ooad.good.domain.GoodsCategory;
import com.ooad.good.domain.GoodsCategoryPo;
import com.ooad.good.util.exception.MallException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodCategoryService {

    /**
     * 得到所有分类
     * @return
     */
    List<GoodsCategoryPo> findAllCategories(Integer page, Integer limit) throws MallException;

    /**
     * 得到所有二级分类
     * @return
     */
    List<GoodsCategoryPo> findAllSecondRankCategory(Integer page, Integer limit) throws MallException;

    /**
     * 得到所有二级分类下的一级
     * @param goodcategoryId
     * @return
     */
    List<GoodsCategoryPo> findSecondRankCategoryByParent(Integer goodcategoryId, Integer page, Integer limit) throws MallException;

    /**
     * 添加分类,是父类还是子类
     * @return 分类
     */
    GoodsCategory addGoodCategory(GoodsCategoryPo goodsCategoryPo) throws MallException;

    /**
     * 通过分类id得到分类
     * @param goodCategoryId 分类
     * @return 分类
     */
    GoodsCategory getGoodsCategoryById(Integer goodCategoryId) throws MallException;

    /**
     * 通过id更新类
     * @param goodCategoryId
     * @param goodsCategory
     * @return
     */
    GoodsCategory updateGoodsCategory(Integer goodCategoryId, GoodsCategory goodsCategory) throws MallException;
    /**
     * 通过Id删除分类
     * @param goodCategoryId
     * @return
     */
    boolean clearGoodCategory(Integer goodCategoryId) throws MallException;

    /**
     * 查找商品
     */
    List<GoodsCategoryPo> findRankOneGoodsCategory(Integer page, Integer limit) throws MallException;

    /**
     * 更改子分类在父分类下的位置
     * @param goodsCategoryId
     * @param goodsCategory
     * @return
     */
    GoodsCategoryPo updateGoodsCategoryPid(Integer goodsCategoryId, GoodsCategory goodsCategory) throws MallException;
}
