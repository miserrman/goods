package com.ooad.good.service;

import com.ooad.good.domain.*;
import com.ooad.good.util.exception.MallException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author wz
 */
@Service
public interface GoodsService {

    /**
     * 根据条件查询商品
     * @param goodSn 商品序列号
     * @param name 商品名
     * @param page 页数
     * @param limit 页数限制
     * @param role 角色
     * @return List<Goods> 商品列表
     * @throws MallException 异常
     */
    List<Goods> userFindGoodsByCondition(String goodSn, String name, Integer page, Integer limit, int role) throws MallException;

    /**
     * 得到所有商品的类别和品牌
     * @param page 页
     * @param limit 页限制
     * @return List<GoodsPo> 商品po
     * @throws MallException 异常
     */
    List<GoodsPo> findAllGoods(Integer page, Integer limit) throws MallException;

    /**
     * 更新商品
     * @param goodsId 商品id
     * @param goods 商品
     * @return goods 商品
     * @throws MallException 异常
     */
    Goods updateGoodsById(Integer goodsId, Goods goods) throws MallException;

    /**
     * 通过Id下架商品，相应的库存和评论也要跟着删除，购物车的商品也要删掉
     * @param goodsId 商品id
     * @param request http请求
     * @throws MallException 异常
     */
    void clearGoodsById(Integer goodsId, HttpServletRequest request) throws MallException;

    /**
     * 添加一个商品
     * @param goods 商品
     * @return goods 商品
     * @throws MallException 异常
     */
    Goods addGoods(Goods goods) throws MallException;


    /**
     * 通过商品Id获取商品的详情
     * @param goodsId 商品Id
     * @param page 页
     * @param limit 页限制
     * @return 商品的po类
     * @throws MallException 异常
     */
    Goods findGoods(Integer goodsId, Integer page, Integer limit) throws MallException;

    /**
     * 进入商品详情界面，如果有userId,则要调用footprint模块进行记录
     * @param userId 用户id
     * @param goodsId 商品id
     * @param role 角色
     * @return goods 商品
     */
    Goods enterGoodsDetail(Integer userId, Integer goodsId, int role);

    /**
     * 在售的商品总数
     * @return 商品总数
     */
    Integer sellingGoods();

    /**
     * 根据商品名查找商品
     * @param name 商品名
     * @param page 页
     * @param limit 页限制
     * @return List<GoodsPo> 商品po列表
     * @throws MallException 异常
     */
    List<GoodsPo> searchGoodsByName(String name, Integer page, Integer limit) throws MallException;

    /**
     * 通过条件查询商品
     * @param goodsSn 商品标识
     * @param name 商品名
     * @param page 页
     * @param limit 页限制
     * @return List<GoodsPo> 商品po列表
     * @throws MallException 异常
     */
    List<GoodsPo> searchGoodsByCondition(String goodsSn, String name, Integer page, Integer limit) throws MallException;

    /**
     * 添加商品下的产品，如果产品已有，就添加库存，如果产品不存在，就新建产品
     * @param goodsId 商品id
     * @param productPo 货品po
     * @return List<ProductPo> 货品po列表
     * @throws MallException 异常
     */
    List<ProductPo> addGoodsProducts(Integer goodsId, ProductPo productPo) throws MallException;

    /**
     * 查找商品下的产品，如果有产品返回产品列表
     * @param goodsId 商品Id
     * @param page 页数
     * @param limit 限制
     * @return List<ProductPo> 货品po列表
     * @throws MallException 异常
     */
    List<ProductPo> findGoodsProduct(Integer goodsId, Integer page, Integer limit) throws MallException;

    /**
     * 管理员修改关于这个产品的信息
     * @param productId 产品Id
     * @param product 产品
     * @return productPo 产品po
     * @throws MallException 异常
     */
    ProductPo updateProduct(Integer productId, Product product) throws MallException;

    /**
     * 管理员清空货品
     * @param productId 货品id
     * @param request http请求
     * @throws MallException 异常
     */
    void clearProduct(Integer productId, HttpServletRequest request) throws MallException;


    /**
     * 得到品牌下的所有商品
     * @param brandId 品牌Id
     * @param page 页数
     * @param limit 限制
     * @param role 角色
     * @return List<GoodsPo> 商品po列表
     * @throws MallException 异常
     */
    List<GoodsPo> findBrandsAllGoods(Integer brandId, Integer page, Integer limit, Integer role) throws MallException;

    /**
     * 得到类别下的所有商品
     * @param goodCategoryId 类别Id
     * @param page 页数
     * @param limit 限制
     * @param role 角色
     * @return List<GoodsPo> 商品po列表
     * @throws MallException 异常
     */
    List<GoodsPo> findGoodsCategoriesAllGoods(Integer goodCategoryId, Integer page, Integer limit, Integer role) throws MallException;

    /**
     * 查找产品
     * @param productId 货品id
     * @return product 货品
     * @throws MallException 异常
     */
    Product findProduct(Integer productId) throws MallException;

    /**
     * 添加产品
     * @param product 货品
     * @throws MallException 异常
     */
    void addProduct(Product product) throws MallException;

    /**
     * 减少库存
     * @param productId 货品id
     * @param quantity 数量
     * @return product 货品
     * @throws MallException 异常
     */
    Product deductProduct(Integer productId, Integer quantity) throws MallException;

    /**
     * 增加库存数量
     * @param productId 货品id
     * @param quantity 数量
     * @return product 货品
     * @throws MallException 异常
     */
    Product addProductNum(Integer productId, Integer quantity) throws MallException;

    /**
     * 更新货品列表
     * @param hashMap hashmap
     * @param type 类型
     * @throws MallException 异常
     */
    void updateListProduct(HashMap<Integer, Integer> hashMap, Integer type) throws MallException;

}
