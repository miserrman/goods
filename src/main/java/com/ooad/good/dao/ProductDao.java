package com.ooad.good.dao;

import com.ooad.good.domain.Product;
import com.ooad.good.mapper.ProductMapper;
import com.ooad.good.util.ResponseCode;
import com.ooad.good.util.ResponseUtil;
import com.ooad.good.util.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. 货品返回的时候是不是要带着商品本身
 * 2.
 */
@Repository
public class ProductDao {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    RedisDao redisDao;

    public Product findProductById(Integer productId) throws MallException {
        Product product = (Product) redisDao.readObjectFromRedis("P_" + productId);
        if (product != null) {
            return product;
        }
        product = productMapper.findProductById(productId);
        if (product == null) {
            throw new MallException(ResponseCode.PRODUCT_UNKNOWN);
        }
        return product;
    }

    public List<Product> findProductByGoodsId(Integer goodsId, Integer page, Integer limit) throws MallException{
        if (page != null && limit != null && (page <= 0 || limit <= 0)) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<Product> productList = productMapper.findProductByGoodsId(goodsId, page, limit);
        if (productList == null || productList.size() == 0) {
            throw new MallException(ResponseCode.GET_PRODUCT_LIST_FAIL);
        }
        return productList;
    }


    public List<Product> addGoodsProduct(Integer goodsId, List<Product> productList){
        List<Product> resList = new ArrayList<>();
        productList.stream().forEach(mallProduct -> {
            mallProduct.setGoodsId(goodsId);
            int res = productMapper.addProduct(mallProduct);
            if (res >= 1) {
                resList.add(mallProduct);
            }
        });
        return resList;
    }

    public Product addProduct(Product mallProduct) throws MallException{
        mallProduct.setGmtCreate(LocalDateTime.now());
        mallProduct.setBeDeleted(false);
        vertifyProduct(mallProduct);
        if (mallProduct.getSpecifications() == null) {
            mallProduct.setSpecifications("default");
        }
        int res = productMapper.addProduct(mallProduct);
        if(res <= 0) {
            throw new MallException(ResponseCode.PRODUCT_ADD_ERROR);
        }
        else {
            return mallProduct;
        }
    }

    public Product addProductStock(Product product, Integer stock) throws MallException {
        product.setSafetyStock(product.getSafetyStock() + stock);
        int res = productMapper.updateProduct(product);
        if (res <= 0) {
            throw new MallException(ResponseCode.PRODUCT_ADD_ERROR);
        } else {
            redisDao.updateObjectFromRedis("P_" + product.getId(), product);
            return productMapper.findProductById(product.getId());
        }
    }

    public Product updateProduct(Integer productId, Product product) throws MallException{
        if(productId != null) {
            product.setId(productId);
        }
        product.setGmtModified(LocalDateTime.now());
        int res = productMapper.updateProduct(product);
        product = productMapper.findProductById(productId);
        if (res <= 0) {
            throw new MallException(ResponseCode.PRODUCT_UPDATE_ERROR);
        } else {
            redisDao.updateObjectFromRedis("P_" + product.getId(), product);
            return product;
        }
    }

    public boolean clearProduct(Integer productId) throws MallException {
        redisDao.clearObjectFromRedis("P_" + productId);
        Product product = this.findProductById(productId);
        product.setGmtModified(LocalDateTime.now());
        product.setBeDeleted(true);
        int res = productMapper.updateProduct(product);
        if (res <= 0) {
            throw new MallException(ResponseCode.PRODUCT_DELETE_ERROR);
        }
        else {
            return true;
        }
    }

    public boolean vertifyProduct(Product product) throws MallException{
        if (product == null) {
            throw new MallException(ResponseCode.PRODUCT_ADD_ERROR);
        }
        if (product.getSafetyStock() == null) {
            throw new MallException(ResponseCode.PRODUCT_ADD_ERROR);
        }
        if(product.getGoodsId() == null) {
            throw new MallException(ResponseCode.PRODUCT_ADD_ERROR);
        } else {
            return true;
        }
    }

    public Product consumeProductStock(Integer productId, Integer num) throws MallException {

        Product product;
        product = productMapper.findProductById(productId);
        product.setSafetyStock(product.getSafetyStock() - num);
        int res = productMapper.updateProduct(product);
        if (res <= 0) {
            throw new MallException(ResponseCode.GET_PRODUCT_STOCK_FAIL);
        }
        redisDao.updateObjectFromRedis("P_" + productId, product);
        product = productMapper.findProductById(productId);
        return product;
    }

    public Product addProductStock(Integer productId, Integer num) throws MallException{
        Product product = null;
        product = productMapper.findProductById(productId);
        Integer safetyStock = product.getSafetyStock();
        if (safetyStock == null) {
            safetyStock = num;
        } else {
            safetyStock = num + safetyStock;
        }
         product.setSafetyStock(safetyStock);
        int res = productMapper.updateProduct(product);
        if (res <= 0) {
            throw new MallException(ResponseCode.GET_PRODUCT_STOCK_FAIL);
        }
        redisDao.updateObjectFromRedis("P_" + productId, product);
        product = productMapper.findProductById(productId);
        return product;
    }
}
