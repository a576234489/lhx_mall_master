package com.lhx.mall.service.impl;

import com.lhx.mall.dao.PmsSkuStockDao;
import com.lhx.mall.mapper.PmsSkuStockMapper;
import com.lhx.mall.model.PmsSkuStock;
import com.lhx.mall.model.PmsSkuStockExample;
import com.lhx.mall.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
        PmsSkuStockExample.Criteria criteria = skuStockExample.createCriteria();
        criteria.andProductIdEqualTo(pid);
        if(!StringUtils.isEmpty(keyword)){
            criteria.andSkuCodeLike("%"+keyword+"%");
        }
        return skuStockMapper.selectByExample(skuStockExample);
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        return skuStockDao.replaceList(skuStockList);
    }
}
