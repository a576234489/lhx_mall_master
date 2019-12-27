package com.lhx.mall.dao;

import com.lhx.mall.dto.PmsProductResult;

/**
 * 商品自定义dao
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     * @param id
     * @return
     */
    PmsProductResult getUpdateInfo(Long id);
}
