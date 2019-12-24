package com.ooad.good.service.impl;

import com.ooad.good.dao.GoodCategoryDao;
import com.ooad.good.dao.GoodsDao;
import com.ooad.good.domain.GoodsCategory;
import com.ooad.good.domain.GoodsCategoryPo;
import com.ooad.good.mapper.GoodsCategoryMapper;
import com.ooad.good.service.GoodCategoryService;
import com.ooad.good.util.ResponseCode;
import com.ooad.good.util.ReverseUtil;
import com.ooad.good.util.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class  GoodCategoryServiceImpl implements GoodCategoryService {

    @Autowired
    GoodCategoryDao goodCategoryDao;

    @Autowired
    GoodsDao goodsDao;

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public List<GoodsCategoryPo> findAllCategories(Integer page, Integer limit) throws MallException {
        List<GoodsCategory> goodsCategoryList = goodCategoryDao.findAllCategories(page, limit);
        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryList.stream().map(ReverseUtil::reverseToGoodsCategory).collect(Collectors.toList());
        return goodsCategoryPos;
    }

    @Override
    public List<GoodsCategoryPo> findAllSecondRankCategory(Integer page, Integer limit) throws MallException {
        List<GoodsCategory> goodsCategoryList = goodCategoryDao.findRankTwoCategories(page, limit);
        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryList.stream().map(ReverseUtil::reverseToGoodsCategory).collect(Collectors.toList());
        return goodsCategoryPos;
    }

    @Override
    public List<GoodsCategoryPo> findSecondRankCategoryByParent(Integer goodcategoryId, Integer page, Integer limit) throws MallException {
        List<GoodsCategory> goodsCategoryList = goodCategoryDao.findRankTwoCategoriesByParent(goodcategoryId, page, limit);
        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryList.stream().map(ReverseUtil::reverseToGoodsCategory).collect(Collectors.toList());
        return goodsCategoryPos;
    }

    @Override
    public GoodsCategory addGoodCategory(GoodsCategoryPo goodsCategoryPo) throws MallException {
        if (goodsCategoryPo.getPid() != null && goodsCategoryPo.getPid() != 0) {
            goodCategoryDao.findGoodCategoryById(goodsCategoryPo.getPid());
        }
        GoodsCategory res = goodCategoryDao.addGoodCategory(new GoodsCategory(goodsCategoryPo));
        return res;
    }

    @Override
    public GoodsCategory getGoodsCategoryById(Integer goodCategoryId) throws MallException{
        GoodsCategory goodsCategory = goodCategoryDao.findGoodCategoryById(goodCategoryId);
        return goodsCategory;
    }

    @Override
    public GoodsCategory updateGoodsCategory(Integer goodCategoryId, GoodsCategory goodsCategory) throws MallException{
        GoodsCategory gory = goodCategoryDao.findGoodCategoryById(goodCategoryId);
        if (goodsCategory.getPid() != null && !goodsCategory.getPid().equals(gory.getPid())) {
            throw new MallException(ResponseCode.GOODSCATEGORY_UPDATE_ERROR);
        }
        GoodsCategory category = goodCategoryDao.updateGoodCategory(goodCategoryId, goodsCategory);
        return category;
    }

    @Override
    public boolean clearGoodCategory(Integer goodCategoryId) throws MallException{
        GoodsCategory goodsCategory = goodCategoryDao.findGoodCategoryById(goodCategoryId);
        if (goodsCategory.getPid() == null || goodsCategory.getPid() == 0) {
            List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.findLevelTwoGoodsCategoryByLevelOneId(goodCategoryId, null, null);
            for(GoodsCategory g : goodsCategoryList) {
                goodCategoryDao.clearGoodCategory(g.getId());
            }
        }
        boolean res = goodCategoryDao.clearGoodCategory(goodCategoryId);
        goodsDao.deletedGoodsCategoryGoodsUpdate(goodCategoryId);
        return res;
    }

    @Override
    public List<GoodsCategoryPo> findRankOneGoodsCategory(Integer page, Integer limit) throws MallException {
        List<GoodsCategory> goodsCategoryList = goodCategoryDao.findRankOneCategories(page, limit);
        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryList.stream()
                .map(ReverseUtil::reverseToGoodsCategory)
                .collect(Collectors.toList());
        return goodsCategoryPos;
    }

    @Override
    public GoodsCategoryPo updateGoodsCategoryPid(Integer goodsCategoryId, GoodsCategory goodsCategory) throws MallException {
        GoodsCategory category = goodCategoryDao.findGoodCategoryById(goodsCategoryId);
        if (category.getPid() == null || category.getPid() == 0) {
            throw new MallException(ResponseCode.GOODSCATEGORY_UPDATE_ERROR);
        }
        if (goodsCategory.getName() != null || goodsCategory.getPicUrl() != null) {
            throw new MallException(ResponseCode.GOODSCATEGORY_UPDATE_ERROR);
        }
        if (goodsCategory.getPid() != null) {
            GoodsCategory g = goodCategoryDao.findGoodCategoryById(goodsCategory.getPid());
            if (g.getPid() != null && g.getPid() != 0) {
                throw new MallException(ResponseCode.GOODSCATEGORY_UPDATE_ERROR);
            }
        }
        GoodsCategory resCategory = goodCategoryDao.updateGoodCategory(goodsCategoryId, goodsCategory);
        return ReverseUtil.reverseToGoodsCategory(resCategory);
    }
}
