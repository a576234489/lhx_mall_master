package com.lhx.mall.dao;

import com.lhx.mall.model.PmsProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductFullReductionDao {
    int insertList(@Param("list")List<PmsProductFullReduction> productFullReductionList);
}
