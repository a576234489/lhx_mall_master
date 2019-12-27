package com.lhx.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhx.mall.mapper.PmsProductAttributeCategoryMapper;
import com.lhx.mall.model.PmsProductAttributeCategory;
import com.lhx.mall.model.PmsProductAttributeCategoryExample;
import com.lhx.mall.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Override
    public List<PmsProductAttributeCategory> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return productAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample());
    }
}
