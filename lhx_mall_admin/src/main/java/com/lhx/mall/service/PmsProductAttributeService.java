package com.lhx.mall.service;

import com.lhx.mall.model.PmsProductAttribute;

import java.util.List;

/**
 * 商品属性管理service
 */
public interface PmsProductAttributeService {
    /**
     * 更具分类id以及类型查询商品参数列表或者规格列表
     * @param cid
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductAttribute> getList(Long cid,Integer type,int pageNum,int pageSize);
}
