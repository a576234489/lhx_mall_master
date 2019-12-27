package com.lhx.mall.dao;

import com.lhx.mall.model.PmsSkuStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品sku库存dao
 */
public interface PmsSkuStockDao {
    int replaceList(@Param("list")List<PmsSkuStock> skuStockList);
}
