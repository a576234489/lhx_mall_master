package com.lhx.mall.service;

import com.lhx.mall.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * 商品属性分类service
 */
public interface PmsProductAttributeCategoryService {
    /**
     * 分页获取商品属性分类
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductAttributeCategory> getList(int pageNum,int pageSize);
}
