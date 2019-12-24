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
     * @param : id -商品的id
     * @return : 单个商品
     * @description : 根据id获取商品
     */
    Goods findGoodsById(Integer id);

    /**
     * @param : 无
     * @return :商品列表
     * @description : 获取所有商品
     */
    List<Goods> findGoods(@Param("page") Integer page,
                          @Param("limit") Integer limit);

    /**
     * @param : id -商品的id
     * @return : 对应的分类
     * @description : 根据商品id获取分类
     */
    GoodsCategory findGoodsCategoryByGoodsId(Integer id);

    /**
     * @param : id -商品的id
     * @return : 对应的品牌
     * @description : 根据商品id获取品牌
     */
    Brand findBrandByGoodsId(Integer id);

    /**
     * @param : goods -商品的对象
     * @return : 状态0或1
     * @description :新建一个商品
     */
    int addGoods(Goods goods);

    /**
     * @param :id -商品的id
     * @return :状态0或1
     * @description :根据id修改商品或删除商品
     */
    int updateGoods(Goods goods);

    /**
     * @param :name -商品的名称
     * @return :商品的列表
     * @description :根据商品name获取商品
     */
    List<Goods> findGoodsByName(@Param("name") String name,
                                @Param("page") Integer page,
                                @Param("limit") Integer limit);

    /**
     * @param :id -类别的id
     * @return :商品的列表
     * @description :根据类别id获取商品
     */
    List<Goods> findGoodsByGoodsCategoryId(@Param("id") Integer id,
                                           @Param("page") Integer page,
                                           @Param("limit") Integer limit,
                                           @Param("role") Integer role);

    /**
     * @param : id -品牌的id
     * @return : 商品的列表
     * @description :根据品牌id获取商品
     */
    List<Goods> findGoodsByBrandId(@Param("id") Integer id,
                                   @Param("page") Integer page,
                                   @Param("limit") Integer limit,
                                   @Param("role") Integer role);



    /**
     * 得到在售的商品总数
     * @return
     */
    List<Goods> findSellingGoods();

    /**
     * 搜索商品
     * @param name
     * @return
     */
    List<Goods> searchGoods(@Param("name") String name,
                            @Param("goodsSn") String goodsSn,
                            @Param("page") Integer page,
                            @Param("limit") Integer limit);

    Integer deletedBrandUpdateGoods(Integer brandId);

    Integer deletedGoodsCategoryUpdateGoods(Integer goodsCategoryId);

}
