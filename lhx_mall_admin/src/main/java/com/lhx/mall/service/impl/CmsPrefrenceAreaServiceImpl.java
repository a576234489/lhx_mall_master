package com.lhx.mall.service.impl;

import com.lhx.mall.mapper.CmsPrefrenceAreaMapper;
import com.lhx.mall.model.CmsPrefrenceArea;
import com.lhx.mall.model.CmsPrefrenceAreaExample;
import com.lhx.mall.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;
    @Override
    public List<CmsPrefrenceArea> listAll() {
        CmsPrefrenceAreaExample prefrenceAreaExample = new CmsPrefrenceAreaExample();
        return prefrenceAreaMapper.selectByExample(prefrenceAreaExample);
    }
}
