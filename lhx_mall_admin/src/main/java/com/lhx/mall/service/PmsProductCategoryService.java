package com.lhx.mall.service;

import com.lhx.mall.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 产品分类service
 */
public interface PmsProductCategoryService {
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
