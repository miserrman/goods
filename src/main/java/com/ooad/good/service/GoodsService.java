package com.ooad.good.service;

import com.ooad.good.domain.*;
import com.ooad.good.util.exception.MallException;
import org.springframework.stereotype.Service;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Service
public interface GoodsService {

    /**
     * 根据条件查询商品
     * @param goodSn 商品序列号
     * @param name 商品名
     * @param page 页数
     * @param limit 页数限制
     * @param role 角色
     * @return
     */
    List<Goods> userFindGoodsByCondition(String goodSn, String name, Integer page, Integer limit, int role) throws MallException;

    /**
     * 得到所有商品的类别和品牌
     * @return List
     */
    List<GoodsPo> findAllGoods(Integer page, Integer limit) throws MallException;


    /**
     * @param goodsId 商品Id
     * @param goods 商品
     * @return 更新是否成功
     */
    Goods updateGoodsById(Integer goodsId, Goods goods) throws MallException;

    /**
     * 通过Id下架商品，相应的库存和评论也要跟着删除，购物车的商品也要删掉
     * @param goodsId 商品Id
     * @return 成功与否
     */
    boolean clearGoodsById(Integer goodsId, HttpServletRequest request) throws MallException;

    /**
     * 添加一个商品
     * @param goods 商品
     * @return
     */
    Goods addGoods(Goods goods) throws MallException;


    /**
     * 通过商品Id获取商品的详情
     *  @param goodsId 商品Id
     * @return 商品的po类
     */
    Goods findGoods(Integer goodsId, Integer page, Integer limit) throws MallException;

    /**
     * 进入商品详情界面，如果有userId,则要调用footprint模块进行记录
     * @param userId
     * @param goodsId
     * @return 商品的详细信息
     */
    Goods enterGoodsDetail(Integer userId, Integer goodsId, int role);

    /**
     * 在售的商品总数
     * @return 商品总数
     */

    Integer sellingGoods();

    List<GoodsPo> searchGoodsByName(String name, Integer page, Integer limit) throws MallException;
    /**
     * 通过条件查询商品
     * @param page 页数
     * @param limit 限制
     * @return
     */
    List<GoodsPo> searchGoodsByCondition(String goodsSn, String name, Integer page, Integer limit) throws MallException;

    /**
     * 添加商品下的产品，如果产品已有，就添加库存，如果产品不存在，就新建产品
     * @param goodsId
     * @param productPo
     * @return
     */
    List<ProductPo> addGoodsProducts(Integer goodsId, ProductPo productPo) throws MallException;

    /**
     * 查找商品下的产品，如果有产品返回产品列表
     * @param goodsId 商品Id
     * @param page 页数
     * @param limit 限制
     * @return
     * @throws MallException
     */
    List<ProductPo> findGoodsProduct(Integer goodsId, Integer page, Integer limit) throws MallException;

    /**
     * 管理员修改关于这个产品的信息
     * @param productId 产品Id
     * @param product 产品
     * @return 修改后的产品信息
     */
    ProductPo updateProduct(Integer productId, Product product) throws MallException;

    /**
     * 管理员清空单品
     * @param productId
     * @return
     * @throws MallException
     */
    boolean clearProduct(Integer productId, HttpServletRequest request) throws MallException;


    /**
     * 得到品牌下的所有商品
     * @param brandId 品牌Id
     * @param page 页数
     * @param limit 限制
     * @return
     */
    List<GoodsPo> findBrandsAllGoods(Integer brandId, Integer page, Integer limit, Integer role) throws MallException;

    /**
     * 得到类别下的所有商品
     * @param goodCategoryId 类别Id
     * @param page 页数
     * @param limit 限制
     * @return
     */
    List<GoodsPo> findGoodsCategoriesAllGoods(Integer goodCategoryId, Integer page, Integer limit, Integer role) throws MallException;

    /**
     * 找到产品
     * @param productId
     * @return
     * @throws MallException
     */
    Product findProduct(Integer productId) throws MallException;

    /**
     * 添加产品
     * @param product
     * @return
     * @throws MallException
     */
    Product addProduct(Product product) throws MallException;

    /**
     * 减少库存
     * @param productId
     * @param quantity
     * @return
     */
    Product deductProduct(Integer productId, Integer quantity) throws MallException;

    /**
     * 增加库存数量
     * @param productId
     * @param quantity
     * @return
     */
    Product addProductNum(Integer productId, Integer quantity) throws MallException;


    void updateListProduct(HashMap<Integer, Integer> hashMap, Integer type) throws MallException;

}
