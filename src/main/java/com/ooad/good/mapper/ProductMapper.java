package com.ooad.good.mapper;
import com.ooad.good.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: FestRam
 * @Description: 产品Mapper
 * @Date: in 21:16 2019/12/6
 * @Modified By:
 */

@Mapper
@Component
public interface ProductMapper {

    /**
     * 用id获得产品
     *
     * @param id 产品id
     * @return 产品
     */
    Product findProductById(Integer id);

    /**
     * 用sku属性获得产品
     *
     * @param specifications 产品的sku属性
     * @return 产品
     */
    Product findProductBySpecifications(String specifications);

    /**
     * 用商品id获得所有产品
     *
     * @param id 商品id
     * @return 产品列表
     */
    List<Product> findProductByGoodsId(@Param("id") Integer id,
                                       @Param("page") Integer page,
                                       @Param("limit") Integer limit);

    /**
     * 更新产品对象
     *
     * @param product 产品
     * @return 0表示不成功，1表示成功
     */
    int updateProduct(Product product);

    /**
     * 新增一个产品
     *
     * @param product 产品
     * @return 0表示不成功，1表示成功
     */
    int addProduct(Product product);
}
