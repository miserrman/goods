package com.ooad.good.mapper;
import com.ooad.good.domain.Brand;
import com.ooad.good.domain.Goods;
import com.ooad.good.domain.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : Hong
 * @description : 商品Mapper
 * @date : create in 16:22 2019/12/06
 * @modified by :
 */

@Mapper
@Component
public interface GoodsMapper {

    /**
     * 根据id获取商品
     * @param id 商品id
     * @return goods 商品
     */
    Goods findGoodsById(Integer id);

    /**
     * 获取所有商品
     * @param page 页
     * @param limit 页限制
     * @return List<Goods> 商品列表
     */
    List<Goods> findGoods(@Param("page") Integer page,
                          @Param("limit") Integer limit);

    /**
     * 根据商品id获取分类
     * @param id 商品id
     * @return goodsCategory 分类
     */
    GoodsCategory findGoodsCategoryByGoodsId(Integer id);

    /**
     * 根据商品id获取品牌
     * @param id 商品id
     * @return brand 品牌
     */
    Brand findBrandByGoodsId(Integer id);

    /**
     * 新建一个商品
     * @param goods 商品
     * @return res 状态
     */
    int addGoods(Goods goods);

    /**
     * 根据id修改商品或删除商品
     * @param goods 商品
     * @return res 状态
     */
    int updateGoods(Goods goods);

    /**
     * 根据商品name获取商品
     * @param name 商品名
     * @param page 页
     * @param limit 页限制
     * @return List<Goods> 商品列表
     */
    List<Goods> findGoodsByName(@Param("name") String name,
                                @Param("page") Integer page,
                                @Param("limit") Integer limit);

    /**
     * 根据类别id获取商品
     * @param id 分类id
     * @param page 页
     * @param limit 页限制
     * @param role 角色
     * @return List<Goods> 商品列表
     */
    List<Goods> findGoodsByGoodsCategoryId(@Param("id") Integer id,
                                           @Param("page") Integer page,
                                           @Param("limit") Integer limit,
                                           @Param("role") Integer role);

    /**
     * 根据品牌id获取商品
     * @param id 品牌id
     * @param page 页
     * @param limit 页限制
     * @param role 角色
     * @return List<Goods> 商品id
     */
    List<Goods> findGoodsByBrandId(@Param("id") Integer id,
                                   @Param("page") Integer page,
                                   @Param("limit") Integer limit,
                                   @Param("role") Integer role);



    /**
     * 得到在售的商品总数
     * @return List<Goods> 商品列表
     */
    List<Goods> findSellingGoods();

    /**
     * 搜索商品
     * @param name 商品名
     * @param goodsSn 商品名标识
     * @param page 页
     * @param limit 页限制
     * @return List<Goods> 商品列表
     */
    List<Goods> searchGoods(@Param("name") String name,
                            @Param("goodsSn") String goodsSn,
                            @Param("page") Integer page,
                            @Param("limit") Integer limit);

    /**
     * 删除品牌更新商品
     * @param brandId 品牌id
     */
    void deletedBrandUpdateGoods(Integer brandId);

    /**
     * 删除分类更新商品
     * @param goodsCategoryId 分类id
     * @return res 状态
     */
    Integer deletedGoodsCategoryUpdateGoods(Integer goodsCategoryId);

}
