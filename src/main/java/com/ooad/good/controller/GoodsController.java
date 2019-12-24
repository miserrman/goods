package com.ooad.good.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import com.ooad.good.domain.*;
import com.ooad.good.service.*;
import com.ooad.good.util.*;
import com.ooad.good.util.exception.MallException;
import com.sun.deploy.security.ValidationState;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.List;


/**
 * @author 1-3 绣春之刃
 */
@RestController
@RequestMapping(value = "", produces = {"application/json;charset=UTF-8"})
public class GoodsController {

    private static final Logger logger =  LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodCategoryService goodCategoryService;

    @Autowired
    BrandService brandService;

    @Autowired
    LogService logService;

    @Autowired
    PictureService pictureService;

    @Autowired
    FootPrintService footPrintService;

    /**

     * 管理员添加商品下的产品

     * @param id

     * @return Product，新添加的产品信息

     */

    @PutMapping("/product/{id}/addstock")
    public Object addStock(@PathVariable Integer id, @RequestParam Integer num) {
        Product product;
        try {
            product = goodsService.addProductNum(id, num);
        } catch (MallException e) {
            logger.debug("错误码" + e.getErrorCode());
            return e.getErrorCode();
        }
        logger.debug("成功添加" + num.toString() + "件库存");
        return ResponseUtil.ok(ReverseUtil.reverseToProductPo(product));
    }

    @PostMapping("/goods/{id}/products")
    public Object addProductByGoodsId(@PathVariable Integer id,
                                      @RequestBody Product product,
                                      HttpServletRequest request) {
        List<ProductPo> productPoList = null;
        try {
            productPoList = goodsService.addGoodsProducts(id, product);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.INSERT, id,
                    "插入商品的产品", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.INSERT, id,
                "插入商品的产品", 0);
        return productPoList;
    }

    /**

     *管理员查询商品下的产品

     * @param id

     * @return List<ProductVo>，所属该商品的产品列表

     */

    @GetMapping("/goods/{id}/products")
    public Object listProductByGoodsId(@PathVariable Integer id,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       HttpServletRequest request) {
        List<ProductPo> productPoList = null;
        try {
             productPoList = goodsService.findGoodsProduct(id, page, limit);
        } catch (MallException e) {
            logger.debug("错误码:" + e.getErrorCode());
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.SELECT, id,
                    "查询商品列表", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.SELECT, id,
                "查询商品列表", 0);
        logger.debug("查询商品下的产品列表为：" + productPoList.toString());
        return ResponseUtil.ok(productPoList);
    }

    /**

     * 管理员修改商品下的某个产品信息

     * @param id


     * @return Product，修改后的产品信息

     */

    @PutMapping("/products/{id}")

    public Object updateProductById(@PathVariable Integer id,
                                    @RequestBody ProductPo proPo,
                                    HttpServletRequest request) {
        if (proPo == null) {
            return ResponseUtil.badArgumentValue();
        }
        Product product = new Product(proPo);
        ProductPo productPo = null;
        try {
            productPo = goodsService.updateProduct(id, product);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.UPDATE, id,
                    "更新产品", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.UPDATE, id,
                "更新产品", 1);
        return ResponseUtil.ok(productPo);
    }



    /**

     * 管理员删除商品下的某个产品信息

     * @param id

     * @return 无（ResponseUtil.ok()即可）

     */

    @DeleteMapping("/products/{id}")
    public Object deleteProductById(@PathVariable Integer id, HttpServletRequest request) {
        try {
            goodsService.clearProduct(id, request);
        } catch (MallException e) {
//            logService.writeAdminLog(request.getIntHeader("userId"),
//                    request.getHeader("ip"), Type.DELETE, id,
//                    "删除产品", 0);
            return e.getErrorCode();
        }
//        logService.writeAdminLog(request.getIntHeader("userId"),
//                request.getHeader("ip"), Type.DELETE, id,
//                "删除产品", 1);
        return ResponseUtil.ok();
    }



//    /**

//     * 获取商品列表

//     * @return

//     */

//    @GetMapping("/goods")

//    public Object listGoods();



    /**

     * 新建/上架一个商品

     *

     * @param goodsPo

     * @return Goods，即新建的一个商品

     */

    @PostMapping("/goods")

    public Object addGoods(@RequestBody GoodsPo goodsPo, HttpServletRequest request) {
        if (goodsPo.getName() == null) {
            return ResponseUtil.fail(ResponseCode.ADD_GOODS_ERROR.getCode(), null);
        }
        Goods goods = new Goods(goodsPo);
        Goods goods1 = null;
        try {
            goods1 = goodsService.addGoods(goods);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.INSERT, goods.getId(),
                    "插入商品", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.INSERT, goods.getId(),
                "插入商品", 1);
        return ResponseUtil.ok(goods1);
    }

    /**

     * 根据条件搜索商品

     *

     * @param name 商品的名字

     * @param page 第几页

     * @param limit 一页多少

    //     * @param sort

    //     * @param order

     * @return

     */

    @GetMapping("/goods")

    public Object listGoods(@RequestParam String name,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit,
                            HttpServletRequest request) {
        List<GoodsPo> goodsPoList = null;
        try {
            goodsPoList = goodsService.searchGoodsByName(name, page, limit);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.SELECT, null,
                "列出商品", 0);
        return ResponseUtil.ok(goodsPoList);
    }



    @GetMapping("/goods/{id}/po")
    public Object getGoodsPo(@PathVariable Integer id) {
        Goods goods = null;
        try {
            goods = goodsService.findGoods(id, null, null);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(ReverseUtil.reverseToGoodsPo(goods));
    }
    /**

     * 根据id获取某个商品

     * @param id

     * @return GoodsVo，即商品的信息，此URL与WX端是同一个URL

     */

    @GetMapping("/goods/{id}")

    public Object getGoodsById(@PathVariable Integer id,
                               HttpServletRequest request) {
        Goods goods = null;
        try {
            goods = goodsService.findGoods(id, null, null);
            footPrintService.documentUserFootPrint(request.getIntHeader("userId"), goods);
            if (goods.getStatusCode() == 0) {
                try {
                    throw new MallException(ResponseCode.GOODS_UNKNOWN);
                } catch (MallException e) {
                    e.printStackTrace();
                }
            }
        } catch (MallException e) {
            return e.getErrorCode();
        }
       return ResponseUtil.ok(goods);
    }

    @GetMapping("admin/goods/{id}")

    public Object adminGetGoodsById(@PathVariable Integer id,
                                    HttpServletRequest request) {
        Goods goods = null;
        try {
            goods = goodsService.findGoods(id, null, null);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.SELECT, id,
                    "管理员查询商品", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.SELECT, id,
                "管理员商品", 1);
        return ResponseUtil.ok(goods);
    }

    @GetMapping("/admin/goods")
    public Object adminGetAllGoods(@RequestParam(required = false) String goodsSn,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit,
                                   HttpServletRequest request) {
        List<GoodsPo> goodsPoList = null;
        try {
            goodsPoList = goodsService.searchGoodsByCondition(goodsSn, name, page, limit);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.SELECT, null,
                "得到所有商品", 1);
        return ResponseUtil.ok(goodsPoList);
    }

    /**

     * 根据id更新商品信息
     * @param id
     * @return Goods，修改后的商品信息
     */
    @PutMapping("/goods/{id}")

    public Object updateGoodsById(@PathVariable Integer id,
                                  @RequestBody GoodsPo goodsPo,
                                  HttpServletRequest request) {
        Goods goods = new Goods(goodsPo);
        Goods g = null;
        try {
            g = goodsService.updateGoodsById(id, goods);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.UPDATE, id,
                    "修改商品", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.UPDATE, id,
                "修改商品", 1);
        return ResponseUtil.ok(g);
    }



    /**

     * 根据id删除商品信息

     * @param id

     * @return  无（即ResponseUtil.ok()即可）

     */

    @DeleteMapping("/goods/{id}")

    public Object deleteGoodsById(@PathVariable Integer id, HttpServletRequest request) {
        try {
            goodsService.clearGoodsById(id, request);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.DELETE, id,
                    "删除商品", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.DELETE, id,
                "删除商品", 1);
        return ResponseUtil.ok();
    }



    /**
     * 获取分类下的商品
     * @param id
     * @return
     */

    @GetMapping("/category/{id}/goods")

    public Object getAllGoodsByCategoryId(@PathVariable Integer id,
                                          @RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer limit,
                                          HttpServletRequest request) {
        List<GoodsPo> goodsPoList = null;
        try {
            goodsPoList = goodsService.findGoodsCategoriesAllGoods(id, page, limit, 0);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.SELECT, id,
                "查询类别", 1);
        return ResponseUtil.ok(goodsPoList);
    }


    /**

     * 根据条件搜索品牌

     * @return List<Brand>

     */

    @GetMapping("/admin/brands")

    public Object listBrand(@RequestParam(required = false) Integer id,
                            @RequestParam(required = false) String name,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit,
                            HttpServletRequest request) {
        List<BrandPo> brandList = null;
        try {
            brandList = brandService.searchBrands(id, name, page, limit);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.SELECT, null,
                "列出品牌", 1);
        return ResponseUtil.ok(brandList);
    }
    /**

     * 创建一个品牌

     * @return

     */

    @PostMapping("/brands")

    public Object addBrand(@RequestBody BrandPo brandPo, HttpServletRequest request) {
        if (brandPo.getName() == null) {
            return ResponseUtil.fail(ResponseCode.BRAND_ADD_ERROR.getCode(), null);
        }
        Brand brand = new Brand(brandPo);
        BrandPo b = null;
        try {
            b = brandService.addOneBrand(brand);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.INSERT, brand.getId(),
                    "添加品牌", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.INSERT, brand.getId(),
                "添加品牌", 1);
        return b;
    }


    /**
     * 管理员查看品牌
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/admin/brands/{id}")
    public Object adminGetBrandById(@PathVariable Integer id, HttpServletRequest request) {
        BrandPo brandPo = null;
        try {
            brandPo = brandService.findBrandById(id);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(brandPo);
    }

    /**
     * 查看品牌详情,此API与商城端/brands/{id}完全相同
     * @param id
     * @return
     */

    @GetMapping("/brands/{id}")

    public Object getBrandById(@PathVariable Integer id, HttpServletRequest request) {
        BrandPo brandPo = null;
        try {
            brandPo = brandService.findBrandById(id);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(brandPo);
    }

    @GetMapping("/brands/{id}/goods")

    public Object getBrandGoods(@PathVariable Integer id,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "2") Integer limit,
                                HttpServletRequest request) {
        List<GoodsPo> goodsPoList = null;
        try {
            goodsPoList = goodsService.findBrandsAllGoods(id, page, limit, 0);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(goodsPoList);
    }

    @GetMapping("/admin/brands/{id}/goods")
    public Object adminGetBrandGoods(@PathVariable Integer id,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit,
                                     HttpServletRequest request) {
        List<GoodsPo> goodsPoList = null;
        try {
            goodsPoList = goodsService.findBrandsAllGoods(id, page, limit, 1);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(goodsPoList);
    }

    /**

     * 修改单个品牌的信息

     * @param id

     * @return

     */

    @PutMapping("/brands/{id}")

    public Object updateBrandById(@PathVariable Integer id,
                                  @RequestBody BrandPo brandPo,
                                  HttpServletRequest request) {
        Brand brand = new Brand(brandPo);
        BrandPo brandRes = null;
        try {
            brandRes = brandService.updateBrandById(id, brand);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.SELECT, id,
                    "更新品牌", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.SELECT, id,
                "更新品牌", 1);
        return ResponseUtil.ok(brandRes);
    }

    /**

     * 删除一个品牌
     * @return
     */

    @DeleteMapping("/brands/{id}")

    public Object deleteBrandById(@PathVariable Integer id, HttpServletRequest request) {
        try {
            brandService.clearBrandById(id);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.DELETE, id,
                    "删除品牌", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.DELETE, id,
                "删除品牌", 1);
        return ResponseUtil.ok();
    }



    /**

     * 查看所有的分类

     * @return

     */

    @GetMapping("/categories")

    public Object listGoodsCategory(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit,
                                    HttpServletRequest request) {
        List<GoodsCategoryPo> goodsCategoryList = null;
        try {
            goodsCategoryList = goodCategoryService.findAllCategories(page, limit);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(goodsCategoryList);
    }

    /**
     * 新建一个分类
     * @return
     */

    @PostMapping("/categories")

    public Object addGoodsCategory(@RequestBody GoodsCategory goodsCategoryPo, HttpServletRequest request) {
        if (goodsCategoryPo.getName() == null) {
            return ResponseUtil.fail(ResponseCode.GOODSCATEGORY_ADD_ERROR.getCode(), null);
        }
        GoodsCategory goodsCategory = new GoodsCategory(goodsCategoryPo);
        GoodsCategory goodsCategoryRes = null;
        try {
            goodsCategoryRes = goodCategoryService.addGoodCategory(goodsCategory);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.INSERT, goodsCategory.getId(),
                    "添加商品类别", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.INSERT, goodsCategory.getId(),
                "添加商品类别", 1);
        return goodsCategoryRes;
    }

    @GetMapping("/admin/categories/{id}/goods")
    public Object adminFindGoodsByGoodsCategoryId(@PathVariable Integer id,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer limit) {
        GoodsCategoryPo goodsCategoryPo = null;
        try {
            GoodsCategory goodsCategory = goodCategoryService.getGoodsCategoryById(id);
            goodsService.findGoodsCategoriesAllGoods(id, page, limit, 1);
            goodsCategoryPo = ReverseUtil.reverseToGoodsCategory(goodsCategory);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(goodsCategoryPo);
    }


    @GetMapping("/categories/{id}")

    public Object getGoodsCategoryById(@PathVariable Integer id, HttpServletRequest request) {
        GoodsCategoryPo goodsCategoryPo = null;
        try {
            GoodsCategory goodsCategory = goodCategoryService.getGoodsCategoryById(id);
            goodsCategoryPo = ReverseUtil.reverseToGoodsCategory(goodsCategory);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(goodsCategoryPo);
    }



    /**

     * 修改分类信息

     * @param id

     * @return

     */

    @PutMapping("/categories/{id}")
    public Object updateGoodsCategoryById(@PathVariable Integer id,
                                          @RequestBody GoodsCategoryPo goodsCategoryPo,
                                          HttpServletRequest request) {
        GoodsCategoryPo goodsCategoryRes = null;
        GoodsCategory goodsCategory = new GoodsCategory(goodsCategoryPo);
        try {
            goodsCategoryRes = goodCategoryService.updateGoodsCategory(id, goodsCategory);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.UPDATE, id,
                    "更新商品类别", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.UPDATE, id,
                "列出商品", 1);
        return goodsCategoryRes;
    }

    @PutMapping("/categories/l2/{id}")
    public Object updateCategoryParent(@PathVariable Integer id, @RequestBody GoodsCategoryPo goodsCategoryPo) {
        GoodsCategory goodsCategory = new GoodsCategory(goodsCategoryPo);
        GoodsCategoryPo res = null;
        try {
            res = goodCategoryService.updateGoodsCategoryPid(id, goodsCategory);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(res);
    }

    /**

     * 删除单个分类

     * @param id

     * @return

     */

    @DeleteMapping("/categories/{id}")

    public Object deleteGoodsCategory(@PathVariable Integer id, HttpServletRequest request) {
        try {
            goodCategoryService.clearGoodCategory(id);
        } catch (MallException e) {
            logService.writeAdminLog(request.getIntHeader("userId"),
                    request.getHeader("ip"), Type.DELETE, id,
                    "删除商品类别", 0);
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.DELETE, id,
                "删除商品类别", 1);
        return ResponseUtil.ok();
    }
    /**

     * 查看所有一级分类

     * @return

     */

    @GetMapping("/categories/l1")

    public Object listOneLevelGoodsCategory(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer limit,
                                            HttpServletRequest request) {
        List<GoodsCategoryPo> goodsCategoryList = null;
        try {
            goodsCategoryList = goodCategoryService.findRankOneGoodsCategory(page, limit);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(goodsCategoryList);
    }



    /**

     * 查看所有品牌

     * @return List<Brand>

     */

    @GetMapping("/brands")
    public Object listBrand(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit,
                            HttpServletRequest request) {
        List<Brand> brandList = null;
        try {
            brandList = brandService.findAllBrands(page, limit);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        logService.writeAdminLog(request.getIntHeader("userId"),
                request.getHeader("ip"), Type.SELECT, null,
                "列出品牌", 1);
        return ResponseUtil.ok(brandList);

    }
    /**

     * 获取当前一级分类下的二级分类

     *

     * @param id 分类类目ID

     * @return 当前分类栏目

     */

    @GetMapping("categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable Integer id,
                                                   @RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer limit,
                                                   HttpServletRequest request) {
        List<GoodsCategoryPo> goodsCategoryList = null;
        try {
            goodsCategoryList = goodCategoryService.findSecondRankCategoryByParent(id, page, limit);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok(goodsCategoryList);
    }



    /**

     * 根据id获得产品对象 - 内部

     * @param id

     * @return

     */

    @GetMapping("/products/{id}/in")

    public Object getProductById(@PathVariable Integer id, HttpServletRequest request) {
        Product product = null;
        try {
            product = goodsService.findProduct(id);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        logger.debug("product: " + JacksonUtil.toJson(product));
        return ResponseUtil.ok(product);
    }


    @GetMapping("/products/{id}/po")
    public Object getProductPoById(@PathVariable Integer id) {
        Product product = null;
        try {
            product = goodsService.findProduct(id);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        ProductPo productPo = ReverseUtil.reverseToProductPo(product);
        return ResponseUtil.ok(productPo);
    }

    @PostMapping("/pic")
    public Object uploadImg(@RequestParam MultipartFile multipartFile) {
        String pictureUrl = null;
        try {
            pictureUrl = pictureService.getPictureUrl(multipartFile);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return pictureUrl;
    }

    /*****************************************************
                           内部接口
     *****************************************************/

    @GetMapping("/goods/{id}/isOnSale")
    public Object isOnSale(@PathVariable Integer id) {
        Goods goods = null;
        try {
            goods = goodsService.findGoods(id, null, null);
        } catch (MallException e) {
            return ResponseUtil.ok(false);
        }
        if (goods.getStatusCode() == 0) {
            return ResponseUtil.ok(false);
        } else {
            return ResponseUtil.ok(true);
        }
    }

    @PutMapping("goods/{id}/deduct")
    public Object deductProduct(@PathVariable Integer id, Integer quantity) {
        Product product = null;
        try {
            product = goodsService.deductProduct(id, quantity);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ReverseUtil.reverseToProductPo(product);
    }

    @PostMapping("/products/stock")
    public Object addProductStock(@RequestBody HashMap<Integer, Integer> decList,
                                  @RequestParam Integer type) {
        try {
            goodsService.updateListProduct(decList, type);
        } catch (MallException e) {
            return e.getErrorCode();
        }
        return ResponseUtil.ok();
    }

}
