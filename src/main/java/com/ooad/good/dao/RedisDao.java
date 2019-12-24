package com.ooad.good.dao;

import com.ooad.good.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {
    @Autowired
    RedisTemplate redisTemplate;

    public Object readObjectFromRedis(String key){
        if(!redisTemplate.hasKey(key)){
            return null;
        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void writeObjectToRedis(String key, Object object){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, object);
    }

    public void clearObjectFromRedis(String key) {
        if (!redisTemplate.hasKey(key))
            return;
        redisTemplate.delete(key);
    }

    public void updateObjectFromRedis(String key, Object object) {
        if (redisTemplate.hasKey(key))
            writeObjectToRedis(key, object);
    }

    public void addProductStock(Integer productId, Integer num){
        String key = "P_" + productId;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            return;
        }
        else {
            Product redisProduct = (Product) valueOperations.get(key);
            Integer safetyStock = redisProduct.getSafetyStock();
            if (safetyStock == null) {
                safetyStock = num;
            }
            else {
                safetyStock = num + safetyStock;
            }
            redisProduct.setSafetyStock(safetyStock);
            valueOperations.set(key, redisProduct);
        }
    }

    public Integer getStockNumInRedis(Integer productId) {
        Product product = (Product) readObjectFromRedis("P_" + productId);
        if (product == null) {
            return null;
        }
        return product.getSafetyStock();
    }

    public void consumeProductStock(Integer productId, Integer num) {
        Product product = (Product) readObjectFromRedis("P_" + productId);
        String key = "P_" + productId;
        if (product == null) {
            return;
        }
        product.setSafetyStock(product.getSafetyStock() - num);
        writeObjectToRedis(key, product);
    }
}
