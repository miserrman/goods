package com.ooad.good.dao;

import com.ooad.good.domain.Goods;
import com.ooad.good.mapper.GoodsMapper;
import com.ooad.good.util.ResponseCode;
import com.ooad.good.util.ResponseUtil;
import com.ooad.good.util.ReverseUtil;
import com.ooad.good.util.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wz
 */
@Repository
public class GoodsDao {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    RedisDao redisDao;

    public List<Goods> searchGoodsByName(String name, Integer page, Integer limit) throws MallException {
        if (page == null || limit == null || page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<Goods> goods = goodsMapper.findGoodsByName(name, page, limit);
        if (goods == null || goods.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODS_LIST_FAIL);
        }
        return goods;
    }

    public Goods findGoodsById(Integer goodsId) throws MallException {
        Goods goods = (Goods) redisDao.readObjectFromRedis("G_" + goodsId);
        if (goods != null) {
            return goods;
        }
        goods = goodsMapper.findGoodsById(goodsId);
        if (goods == null) {
            throw new MallException(ResponseCode.GOODS_UNKNOWN);
        }
        redisDao.writeObjectToRedis("G_" + goodsId, goods);
        return goods;
    }

    public List<Goods> findAllGoods(Integer page, Integer limit) throws MallException {
        if (page == null || limit == null || page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<Goods> goodsList = goodsMapper.findGoods(page, limit);
        if (goodsList == null || goodsList.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODS_LIST_FAIL);
        }
        return goodsList;
    }

    public List<Goods> findGoodsByBrandId(Integer brandId, Integer page, Integer limit, Integer role) throws MallException{
        if (page != null || limit != null) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        if (page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<Goods> goodsList = goodsMapper.findGoodsByBrandId(brandId, page, limit, role);
        if (goodsList == null || goodsList.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODS_LIST_FAIL);
        }
        return goodsList;
    }

    public List<Goods> findGoodsByGoodCategoryId(Integer goodCategoryId, Integer page, Integer limit, Integer role) throws MallException{
        if (page != null || limit != null) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        if (page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<Goods> goodsList = goodsMapper.findGoodsByGoodsCategoryId(goodCategoryId, page, limit, role);
        if (goodsList == null || goodsList.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODS_LIST_FAIL);
        }
        return goodsList;
    }


    public List<Goods> searchGoods(String name, String goodsSn, Integer page, Integer limit) throws MallException{
        if (page == null || limit == null ||page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<Goods> goodsList = goodsMapper.searchGoods(name, goodsSn, page, limit);
        if (goodsList == null || goodsList.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODS_LIST_FAIL);
        }
        return goodsList;
    }

    public Integer findGoodsSelling() {
        List<Goods> goodsList = goodsMapper.findSellingGoods();
        return goodsList.size();
    }

    public Goods addGoods(Goods goods) throws MallException {
        if (goods.getName() == null) {
            throw new MallException(ResponseCode.ADD_GOODS_ERROR);
        }
        goods.setGmtCreate(LocalDateTime.now());
        goods.setBeDeleted(false);
        goods.setStatusCode(1);
        int res = goodsMapper.addGoods(goods);
        if (res <= 0) {
            throw new MallException(ResponseCode.ADD_GOODS_ERROR);
        } else {
            return goods;
        }
    }

    public Goods updateGoods(Integer goodsId, Goods goods, Integer type) throws MallException {
        if (goodsId != null) {
            goods.setId(goodsId);
        }
        goods.setGmtModified(LocalDateTime.now());
        int res = goodsMapper.updateGoods(goods);
        if (res <= 0) {
            if (type == 0) {
                throw new MallException(ResponseCode.UPDATE_GOODS_ERROR);
            } else {
                throw new MallException(ResponseCode.UNDER_SHELF_ERROR);
            }
        }
        else {
            goods = goodsMapper.findGoodsById(goodsId);
            redisDao.updateObjectFromRedis("G_" + goodsId, goods);
            return goods;
        }
    }

    public boolean clearGoods(Integer goodsId) throws MallException{
        redisDao.clearObjectFromRedis("G_" + goodsId);
        Goods goods = this.findGoodsById(goodsId);
        goods.setStatusCode(0);
        goods.setBeDeleted(true);
        goods.setGmtModified(LocalDateTime.now());
        int res = goodsMapper.updateGoods(goods);
        if (res <= 0) {
            throw new MallException(ResponseCode.DELETE_GOODS_ERROR);
        } else {
            return true;
        }
    }

    public boolean deletedBrandGoodsUpdate(Integer brandId) throws MallException {
        goodsMapper.deletedBrandUpdateGoods(brandId);
        return true;
    }

    public boolean deletedGoodsCategoryGoodsUpdate(Integer goodsCategoryId) throws MallException {
            int res = goodsMapper.deletedGoodsCategoryUpdateGoods(goodsCategoryId);
            return true;

    }
}
