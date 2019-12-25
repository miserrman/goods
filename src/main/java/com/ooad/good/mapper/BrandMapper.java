package com.ooad.good.mapper;
import com.ooad.good.domain.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: FestRam
 * @Description: 品牌Mapper
 * @Date: in 21:14 2019/12/6
 * @Modified By:
 */

@Mapper
@Component
public interface BrandMapper {

    /**
     * 用id获得品牌
     *
     * @param id 品牌id
     * @return 品牌
     */
    Brand findBrandById(Integer id);

    /**
     * 用品牌名获得品牌
     *
     * @param name 品牌名
     * @return 品牌
     */
    Brand findBrandByName(String name);

    /**
     * 得到所有品牌
     * @param page -页数
     * @param limit -页限制
     * @return 品牌列表
     */
    List<Brand> findAllBrands(@Param("page") Integer page,
                              @Param("limit") Integer limit);

    /**
     * 更新品牌对象
     *
     * @param brand 品牌
     * @return 0表示不成功，1表示成功
     */
    int updateBrand(Brand brand);

    /**
     * 新增一个品牌
     *
     * @param  brand 品牌
     * @return 0表示不成功，1表示成功
     */
    int addBrand(Brand brand);

    /**
     * 通过名称查找品牌
     * @param id 品牌id
     * @param name 品牌名
     * @param page 页
     * @param limit 页限制
     * @return List<Brand> 品牌列表
     */
    List<Brand> searchBrandByName(@Param("id") Integer id,
                                  @Param("name") String name,
                                  @Param("page") Integer page,
                                  @Param("limit") Integer limit);
}
