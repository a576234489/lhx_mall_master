package com.lhx.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhx.mall.mapper.PmsProductAttributeMapper;
import com.lhx.mall.model.PmsProductAttribute;
import com.lhx.mall.model.PmsProductAttributeExample;
import com.lhx.mall.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {
    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;
    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PmsProductAttributeExample productAttributeExample = new PmsProductAttributeExample();
        productAttributeExample.setOrderByClause("sort desc");
        PmsProductAttributeExample.Criteria criteria = productAttributeExample.createCriteria();
        criteria.andProductAttributeCategoryIdEqualTo(cid);
        criteria.andTypeEqualTo(type);
        return productAttributeMapper.selectByExample(productAttributeExample);
    }
}
