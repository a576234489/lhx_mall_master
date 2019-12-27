package com.lhx.mall.service;


import com.lhx.mall.dto.PmsProductParam;
import com.lhx.mall.dto.PmsProductQueryParam;
import com.lhx.mall.dto.PmsProductResult;
import com.lhx.mall.model.PmsProduct;

import java.util.List;

/**
 * 商品管理service
 */
public interface PmsProductService {
    /**
     * 分页查询商品
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam,Integer pageNum,Integer pageSize);

    /**
     * 商品批量上下架
     */
    int updatePublishStatus(List<Long> ids,Integer publishStatus);
    /**
     * 批量设为/取消新品
     */
    int updateNewStatus(List<Long> ids,Integer newStatus);
    /**
     * 批量设为/取消推荐
     */
    int updateRecommandStatus(List<Long> ids,Integer recommandStatus);

    /**
     * 更具商品id获取商品编辑信息
     * @param id
     * @return
     */
    PmsProductResult getUpdateInfo(Long id);

    /**
     * 更新商品
     * @param id
     * @param productParam
     * @return
     */
    int update(Long id, PmsProductParam productParam);
}
