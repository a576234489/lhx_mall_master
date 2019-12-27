package com.lhx.mall.dao;

import com.lhx.mall.model.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsMemberPriceDao {
    int insetList(@Param("list")List<PmsMemberPrice> memberPriceList);
}
