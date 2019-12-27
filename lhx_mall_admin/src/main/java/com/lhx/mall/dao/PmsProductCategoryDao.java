package com.lhx.mall.dao;

import com.lhx.mall.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 产品分类自定义dao
 */
public interface PmsProductCategoryDao {
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
