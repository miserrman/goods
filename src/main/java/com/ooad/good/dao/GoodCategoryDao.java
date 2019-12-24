package com.ooad.good.dao;

import com.ooad.good.domain.GoodsCategory;
import com.ooad.good.domain.GoodsCategoryPo;
import com.ooad.good.mapper.GoodsCategoryMapper;
import com.ooad.good.util.ResponseCode;
import com.ooad.good.util.ResponseUtil;
import com.ooad.good.util.ReverseUtil;
import com.ooad.good.util.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GoodCategoryDao {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    RedisDao redisDao;

    public List<GoodsCategory> findAllCategories(Integer page, Integer limit) throws MallException{
        if ((page == null || limit == null) || page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.findGoodsCategory(page, limit);
        if (goodsCategoryList == null || goodsCategoryList.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODSCATEGORY_LIST_FAIL);
        }
        return goodsCategoryList;
    }

    public List<GoodsCategory> findRankOneCategories(Integer page, Integer limit) throws MallException{
        if (page == null || limit == null || page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
       List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.findLevelOneGoodsCategory(page, limit);
        if (goodsCategoryList == null || goodsCategoryList.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODSCATEGORY_LIST_FAIL);
        }
       return goodsCategoryList;
    }

    public List<GoodsCategory> findRankTwoCategories(Integer page, Integer limit) throws MallException{
        if (page == null || limit == null || page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.findLevelTwoGoodsCategory(page, limit);
        if (goodsCategoryList == null || goodsCategoryList.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODSCATEGORY_LIST_FAIL);
        }
        return goodsCategoryList;
    }

    public List<GoodsCategory> findRankTwoCategoriesByParent(Integer parentId, Integer page, Integer limit) throws MallException{
        if (page == null || limit == null || page <= 0 || limit <= 0) {
            throw new MallException(ResponseCode.BAD_ARGUMENT);
        }
        List<GoodsCategory> childCategoryList = goodsCategoryMapper.findLevelTwoGoodsCategoryByLevelOneId(parentId, page, limit);
        if (childCategoryList == null || childCategoryList.size() == 0) {
            throw new MallException(ResponseCode.GET_GOODSCATEGORY_LIST_FAIL);
        }
        return childCategoryList;
    }

    public GoodsCategory findGoodCategoryById(Integer goodCategoryId) throws MallException {
        GoodsCategory goodsCategory = (GoodsCategory) redisDao.readObjectFromRedis("C_" + goodCategoryId);
        if (goodsCategory != null) {
            return goodsCategory;
        }
        goodsCategory = goodsCategoryMapper.findGoodsCategoryById(goodCategoryId);
        if (goodsCategory == null) {
            throw new MallException(ResponseCode.GOODSCATEGORY_UNKNOWN);
        }
        redisDao.writeObjectToRedis("C_" + goodCategoryId, goodsCategory);
        return goodsCategory;
    }

    public GoodsCategory addGoodCategory(GoodsCategory goodsCategory) throws MallException {
        if (goodsCategory.getPid() == null) {
            goodsCategory.setPid(0);
        }
        goodsCategory.setGmtCreate(LocalDateTime.now());
        goodsCategory.setBeDeleted(false);
        int res = goodsCategoryMapper.addGoodsCategory(goodsCategory);
        if (res <= 0) {
            throw new MallException(ResponseCode.GOODSCATEGORY_ADD_ERROR);
        }
        else {
            return goodsCategory;
        }
    }

    public GoodsCategory updateGoodCategory(Integer goodCategoryId, GoodsCategory goodsCategory) throws MallException{
        if (goodCategoryId != null) {
            goodsCategory.setId(goodCategoryId);
        }
        goodsCategory.setGmtModified(LocalDateTime.now());
        int res = goodsCategoryMapper.updateGoodsCategory(goodsCategory);
        if (res <= 0){
            throw new MallException(ResponseCode.GOODSCATEGORY_UPDATE_ERROR);
        }
        else {
            goodsCategory = goodsCategoryMapper.findGoodsCategoryById(goodCategoryId);
            redisDao.updateObjectFromRedis("C_" + goodCategoryId, goodsCategory);
            return goodsCategory;
        }
    }

    public boolean clearGoodCategory(Integer goodCategoryId) throws MallException{
        redisDao.clearObjectFromRedis("C_" + goodCategoryId);
        GoodsCategory goodsCategory = this.findGoodCategoryById(goodCategoryId);
        List<GoodsCategory> goodsCategoryList = new ArrayList<>();
        if (goodsCategory.getPid() == null || goodsCategory.getPid() == 0) {
            goodsCategoryList = goodsCategoryMapper.findLevelTwoGoodsCategoryByLevelOneId(goodCategoryId, null, null);
        }
        for (GoodsCategory g : goodsCategoryList) {
            g.setBeDeleted(true);
            g.setGmtModified(LocalDateTime.now());
            goodsCategoryMapper.updateGoodsCategory(g);
        }
        goodsCategory.setBeDeleted(true);
        goodsCategory.setGmtModified(LocalDateTime.now());
        int res = goodsCategoryMapper.updateGoodsCategory(goodsCategory);
        if (res <= 0) {
            throw new MallException(ResponseCode.DELETE_GOODS_ERROR);
        }
        else {
            return true;
        }
    }
}
