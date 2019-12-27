package com.lhx.mall.service.impl;

import com.lhx.mall.dao.PmsProductCategoryDao;
import com.lhx.mall.dto.PmsProductCategoryWithChildrenItem;
import com.lhx.mall.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsProductCategoryService实现类
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Autowired
    private PmsProductCategoryDao productCategoryDao;
    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }
}
