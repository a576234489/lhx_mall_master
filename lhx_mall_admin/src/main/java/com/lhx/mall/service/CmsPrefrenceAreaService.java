package com.lhx.mall.service;

import com.lhx.mall.model.CmsPrefrenceArea;

import java.util.List;

/**
 * 商品优选专区service
 */
public interface CmsPrefrenceAreaService {
    /**
     * 查询所有商品优选专区
     * @return
     */
    List<CmsPrefrenceArea> listAll();
}
