package com.lhx.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhx.mall.mapper.PmsBrandMapper;
import com.lhx.mall.model.PmsBrand;
import com.lhx.mall.model.PmsBrandExample;
import com.lhx.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.setOrderByClause("sort desc");
        PmsBrandExample.Criteria criteria = pmsBrandExample.createCriteria();
        if(!StringUtils.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword +"%");
        }
        return brandMapper.selectByExample(pmsBrandExample);
    }
}
