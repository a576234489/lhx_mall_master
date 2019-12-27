package com.lhx.mall.dao;

import com.lhx.mall.model.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductLadderDao {
    int insertList(@Param("list")List<PmsProductLadder> productLadderList);
}
