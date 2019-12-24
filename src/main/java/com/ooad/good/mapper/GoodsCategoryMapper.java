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
     * @param : 无
     * @return : 分类的列表
     * @description : 查看所有的分类
     */
    List<GoodsCategory> findGoodsCategory(@Param("page") Integer page,
                                          @Param("limit") Integer limit);

    /**
     * @param :id -分类的id
     * @return : 分类
     * @description : 根据id查看单个分类
     */
    GoodsCategory findGoodsCategoryById(Integer id);

    /**
     * @param : goodsCategory 分类对象
     * @return : 状态0或1
     * @description : 新建一个分类
     */
    int addGoodsCategory(GoodsCategory goodsCategory);

    /**
     * @param : goodsCategory 分类对象
     * @return : 状态0或1
     * @description : 根据id修改单个分类或删除单个分类
     */
    int updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * @param : 无
     * @return : 一级商品分类的列表
     * @description : 查看所有一级分类
     */
    List<GoodsCategory> findLevelOneGoodsCategory(@Param("page") Integer page,
                                                  @Param("limit") Integer limit);


    /**
     * @param : 无
     * @return : 二级商品分类的列表
     * @description : 查看所有二级分类
     */
    List<GoodsCategory> findLevelTwoGoodsCategory(@Param("page") Integer page,
                                                  @Param("limit") Integer limit);

    /**
     * @param : id -当前一级分类的id
     * @return : 二级商品分类的列表
     * @description : 查看当前一级分类下的所有二级分类
     */

    List<GoodsCategory> findLevelTwoGoodsCategoryByLevelOneId(@Param("id") Integer id,
                                                              @Param("page") Integer page,
                                                              @Param("limit") Integer limit);

}
