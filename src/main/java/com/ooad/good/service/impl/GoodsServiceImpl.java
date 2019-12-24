package com.ooad.good.service.impl;

import com.ooad.good.dao.BrandDao;
import com.ooad.good.dao.GoodCategoryDao;
import com.ooad.good.dao.GoodsDao;
import com.ooad.good.dao.ProductDao;
import com.ooad.good.domain.*;
import com.ooad.good.mapper.ProductMapper;
import com.ooad.good.service.*;
import com.ooad.good.util.ResponseCode;
import com.ooad.good.util.ResponseUtil;
import com.ooad.good.util.ReverseUtil;
import com.ooad.good.util.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wz
 * 1. 用户是否可以查看已下架的商品
 * 2. 上架商品的逻辑比较混乱
 * 3.如果增加的商品有Id属性不为空怎么办
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    CommentService commentService;

    @Autowired
    GoodsDao goodsDao;

    @Autowired
    BrandDao brandDao;

    @Autowired
    GoodCategoryDao goodCategoryDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    BrandService brandService;

    @Autowired
    GoodCategoryService goodCategoryService;

    @Autowired
    ProductMapper productMapper;



    @Override
    public List<Goods> userFindGoodsByCondition(String goodSn, String name, Integer page, Integer limit, int role) {
        return null;
    }

    /**
     * 找到所有Goods的详细信息，包括品牌，类别和产品
     * @param page 页数
     * @param limit 页数限制
     * @return Goods详细信息列表
     */
    @Override
    public List<GoodsPo> findAllGoods(Integer page, Integer limit) throws MallException {
        List<Goods> goodsList = goodsDao.findAllGoods(page, limit);
        List<GoodsPo> goodsPoList = goodsList.stream().map(ReverseUtil::reverseToGoodsPo).collect(Collectors.toList());
        return goodsPoList;
    }

    /**
     * 更新商品信息
     * @param goodsId 商品Id
     * @param goods 商品
     * @return
     */
    @Override
    public Goods updateGoodsById(Integer goodsId, Goods goods) throws MallException {
        int flag = 0;
        Goods hasGoods = goodsDao.findGoodsById(goodsId);
        if (goods.getStatusCode() != null) {
            if (hasGoods.getStatusCode().equals(hasGoods.getStatusCode())) {
                flag = 1;
            }
        }
        if (goods.getBrandId() != null) {
            brandDao.findBrandById(goods.getBrandId());
        }
        if (goods.getGoodsCategoryId() != null) {
            goodCategoryDao.findGoodCategoryById(goods.getGoodsCategoryId());
        }
        Goods goods1 = goodsDao.updateGoods(goodsId, goods, flag);
        return goods1;
    }

    /**
     * 清除商品，需要清除所有货品信息,清空购物车的对应产品，订单是否受影响?
     * @param goodsId 商品Id
     * @return
     */
    @Override
    public boolean clearGoodsById(Integer goodsId, HttpServletRequest request) throws MallException{
        goodsDao.clearGoods(goodsId);
        List<Product> products = productDao.findProductByGoodsId(goodsId, null, null);
        List<Integer> productIdList = products.stream().map(Product::getId).collect(Collectors.toList());
        for(Integer id : productIdList) {
            productDao.clearProduct(id);
        }
        //commentService.deleteCommentByProductIdList(productIdList, request.getIntHeader("userId"), request.getHeader("ip"));
        return true;
    }


    @Override
    public Goods addGoods(Goods goods) throws MallException {
        Goods g = null;
        Brand brand = null;
        if (goods.getBrandId() != null) {
            brand = brandService.findBrandById(goods.getBrandId());
        }
        GoodsCategory goodsCategory = null;
        if (goods.getBrandId() != null) {
            goodsCategory = goodCategoryService.getGoodsCategoryById(goods.getGoodsCategoryId());
        }
        goods = goodsDao.addGoods(goods);
        goods.setGoodsCategoryPo(goodsCategory);
        goods.setBrandPo(brand);
        return goods;
    }

    @Override
    public Goods enterGoodsDetail(Integer userId, Integer goodsId, int role) {
        //Goods goods = getGoodsDetail(goodsId, role);
        return null;
    }

    /**
     * 显示在售的商品,在售的商品有两步，一次是筛选掉所有未上架的商品，一次筛选掉没有product的商品
     * @return
     */
    @Override
    public Integer sellingGoods() {
        Integer goodsNum = goodsDao.findGoodsSelling();
        return goodsNum;
    }

    @Override
    public List<GoodsPo> searchGoodsByName(String name, Integer page, Integer limit) throws MallException {
        if (name == null || name == "") {
            throw new MallException(ResponseCode.GET_GOODS_LIST_FAIL);
        }
        List<Goods> goodsList = goodsDao.searchGoodsByName(name, page, limit);
        List<GoodsPo> goodsPoList = goodsList.stream().map(ReverseUtil::reverseToGoodsPo).collect(Collectors.toList());
        return goodsPoList;
    }

    // 改成sql
    @Override
    public List<GoodsPo> searchGoodsByCondition(String goodsSn, String name, Integer page, Integer limit) throws MallException {
        List<Goods> goodsList = goodsDao.searchGoods(name, goodsSn, page, limit);
        List<GoodsPo> goodsPoList = goodsList.stream().map(ReverseUtil::reverseToGoodsPo).collect(Collectors.toList());
        return goodsPoList;
    }

    @Override
    public List<ProductPo> findGoodsProduct(Integer goodsId, Integer page, Integer limit) throws MallException  {
        goodsDao.findGoodsById(goodsId);
        List<Product> products = productDao.findProductByGoodsId(goodsId, page, limit);
        List<ProductPo> productPoList = products.stream().map(ReverseUtil::reverseToProductPo).collect(Collectors.toList());
        return productPoList;
    }

    @Override
    public List<ProductPo> addGoodsProducts(Integer goodsId, ProductPo productPo) throws MallException {
        goodsDao.findGoodsById(goodsId);
        productPo.setGoodsId(goodsId);
        Integer productId = productPo.getId();
        if (productId == null) {
            Product product = new Product(productPo);
            product.setGoodsId(goodsId);
            productDao.addProduct(product);
        }
        else {
            Product product = productDao.findProductById(productId);
            productDao.addProductStock(product, productPo.getSafetyStock());
        }
        List<ProductPo> goodsPoList = this.findGoodsProduct(goodsId, null, null);
        return goodsPoList;
    }

    @Override
    public ProductPo updateProduct(Integer productId, Product product) throws MallException {
        productDao.findProductById(productId);
        Product p = productDao.updateProduct(productId, product);
        return ReverseUtil.reverseToProductPo(p);
    }

    @Override
    public boolean clearProduct(Integer productId, HttpServletRequest request) throws MallException {
        productDao.findProductById(productId);
        request.setAttribute("userId", 12);
        commentService.deleteCommentByProductId(productId, request.getIntHeader("userId"), request.getHeader("ip"));
        boolean res = productDao.clearProduct(productId);
        return res;
    }

    @Override
    public List<GoodsPo> findBrandsAllGoods(Integer brandId, Integer page, Integer limit, Integer role) throws MallException {
        brandService.findBrandById(brandId);
        List<Goods> goodsList = goodsDao.findGoodsByBrandId(brandId, page, limit, role);
        if (goodsList == null) {
            return null;
        }
        List<GoodsPo> goodsPos = goodsList.stream().map(ReverseUtil::reverseToGoodsPo).collect(Collectors.toList());
        return goodsPos;
    }

    @Override
    public List<GoodsPo> findGoodsCategoriesAllGoods(Integer goodCategoryId, Integer page, Integer limit, Integer role) throws MallException{
        goodCategoryService.getGoodsCategoryById(goodCategoryId);
        List<Goods> goodsList = goodsDao.findGoodsByGoodCategoryId(goodCategoryId, page, limit, role);
        List<GoodsPo> goodsPoList = goodsList.stream().map(ReverseUtil::reverseToGoodsPo).collect(Collectors.toList());
        return goodsPoList;
    }

    @Override
    public Product findProduct(Integer productId) throws MallException {
        Product product = productDao.findProductById(productId);
        Goods goods = goodsDao.findGoodsById(product.getGoodsId());
        BrandPo brandPo = null;
        if (goods.getBrandId() != null) {
            brandPo = ReverseUtil.reverseToBrandPo(brandDao.findBrandById(goods.getBrandId()));
        }
        GoodsCategoryPo goodsCategoryPo = null;
        if (goods.getGoodsCategoryId() != null) {
            goodsCategoryPo = ReverseUtil.reverseToGoodsCategory(goodCategoryDao.findGoodCategoryById(goods.getGoodsCategoryId()));
        }
        goods.setBrandPo(brandPo);
        goods.setGoodsCategoryPo(goodsCategoryPo);
        List<ProductPo> productPoList = this.findGoodsProduct(goods.getId(), null, null);

        goods.setProductPoList(productPoList);
        product.setGoods(goods);
        return product;
    }

    @Override
    public Product addProduct(Product product) throws MallException {
        Product pro = null;
        if (product.getId() == null) {
            pro = productDao.addProduct(product);
        }
        else {
            pro = productDao.findProductById(product.getId());
            if (pro.getSafetyStock() == null) {
                pro.setSafetyStock(product.getSafetyStock());
            }
            else {
                pro.setSafetyStock(pro.getSafetyStock() + product.getSafetyStock());
            }
            productMapper.updateProduct(pro);
            pro = productMapper.findProductById(pro.getId());
        }
        return pro;
    }

    @Override
    public Product deductProduct(Integer productId, Integer quantity) throws MallException{
        Product product = productDao.findProductById(productId);
        if (product == null || product.getSafetyStock() == null || product.getSafetyStock() < quantity) {
            throw new MallException(ResponseCode.GET_PRODUCT_STOCK_FAIL);
        }
        Product proRes = productDao.consumeProductStock(productId, quantity);
        return proRes;
    }

    @Override
    public Product addProductNum(Integer productId, Integer quantity) throws MallException {
        productDao.findProductById(productId);
        Product product = productDao.addProductStock(productId, quantity);
        return product;
    }

    @Override
    public void updateListProduct(HashMap<Integer, Integer> hashMap, Integer type) throws MallException {
        if (type == 1) {
            for(Integer productId : hashMap.keySet()) {
                this.addProductNum(productId, hashMap.get(productId));
            }
        }
        else {
            for(Integer productId : hashMap.keySet()) {
                this.deductProduct(productId, hashMap.get(productId));
            }
        }
    }

    @Override
    public Goods findGoods(Integer goodsId, Integer page, Integer limit) throws MallException {
        Goods goods = goodsDao.findGoodsById(goodsId);
        GoodsCategory goodsCategory = null;
        if (goods.getGoodsCategoryId() != null) {
            goodsCategory = goodCategoryDao.findGoodCategoryById(goods.getGoodsCategoryId());
        }
        Brand brand = null;
        if (goods.getBrandId() != null) {
            brand = brandDao.findBrandById(goods.getBrandId());
        }
        List<ProductPo> productPoList = this.findGoodsProduct(goodsId, page, limit);
        goods.setBrandPo(brand);
        goods.setGoodsCategoryPo(goodsCategory);
        goods.setProductPoList(productPoList);
        return goods;
    }

}
