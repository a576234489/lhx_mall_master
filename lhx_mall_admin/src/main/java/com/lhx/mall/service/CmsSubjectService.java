package com.lhx.mall.service;

import com.lhx.mall.model.CmsSubject;

import java.util.List;

/**
 * 商品专区service
 */
public interface CmsSubjectService {
    /**
     * 获取所有商品专区
     * @return
     */
    List<CmsSubject> listAll();
}
