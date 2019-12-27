package com.lhx.mall.service;

import com.lhx.mall.model.PmsSkuStock;

import java.util.List;

/**
 * sku库存管理service
 */
public interface PmsSkuStockService {
    /**
     * 更具商品id和sku编码模糊查询sku库存
     */
    List<PmsSkuStock> getList(Long pid,String keyword);

    /**
     * 批量修改sku库存
     * @param pid
     * @param skuStockList
     * @return
     */
    int update(Long pid,List<PmsSkuStock> skuStockList);
}
