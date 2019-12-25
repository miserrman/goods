package com.ooad.good.mapper;

import com.ooad.good.domain.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : Hong
 * @description : 商品分类Mapper
 * @date : create in 16:22 2019/12/06
 * @modified by :
 */

@Mapper
@Component
public interface GoodsCategoryMapper {

    /**
     * 查找分类
     * @param page 页
     * @param limit 页限制
     * @return List<GoodsCategory> 分类列表
     */
    List<GoodsCategory> findGoodsCategory(@Param("page") Integer page,
                                          @Param("limit") Integer limit);

    /**
     * 根据id查看单个分类
     * @param id 分类id
     * @return goodsCategory 货品分类
     */
    GoodsCategory findGoodsCategoryById(Integer id);

    /**
     * 新建一个分类
     * @param goodsCategory 分类
     * @return res 状态
     */
    int addGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 根据id修改单个分类或删除单个分类
     * @param goodsCategory 分类
     * @return res 状态
     */
    int updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 查看所有一级分类
     * @param page 页
     * @param limit 页限制
     * @return List<GoodsCategory> 分类列表
     */
    List<GoodsCategory> findLevelOneGoodsCategory(@Param("page") Integer page,
                                                  @Param("limit") Integer limit);

    /**
     * 查看所有二级分类
     * @param page 页
     * @param limit 页限制
     * @return List<GoodsCategory> 分类列表
     */
    List<GoodsCategory> findLevelTwoGoodsCategory(@Param("page") Integer page,
                                                  @Param("limit") Integer limit);

    /**
     * 查看当前一级分类下的所有二级分类
     * @param id 分类id
     * @param page 页
     * @param limit 页限制
     * @return List<GoodsCategory> 分类列表
     */
    List<GoodsCategory> findLevelTwoGoodsCategoryByLevelOneId(@Param("id") Integer id,
                                                              @Param("page") Integer page,
                                                              @Param("limit") Integer limit);

}
