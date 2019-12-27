package com.lhx.mall.service.impl;

import com.lhx.mall.mapper.CmsSubjectMapper;
import com.lhx.mall.model.CmsSubject;
import com.lhx.mall.model.CmsSubjectExample;
import com.lhx.mall.service.CmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {
    @Autowired
    private CmsSubjectMapper subjectMapper;
    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectByExample(new CmsSubjectExample());
    }
}
