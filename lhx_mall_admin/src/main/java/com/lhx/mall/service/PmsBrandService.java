package com.lhx.mall.service;

import com.lhx.mall.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    List<PmsBrand> listBrand(String keyword,int pageNum,int pageSize);
}
