package com.lhx.mall.service.impl;

import com.github.pagehelper.PageHelper;

import com.lhx.mall.dao.PmsMemberPriceDao;
import com.lhx.mall.dao.PmsProductDao;
import com.lhx.mall.dao.PmsProductFullReductionDao;
import com.lhx.mall.dao.PmsProductLadderDao;
import com.lhx.mall.dto.PmsProductParam;
import com.lhx.mall.dto.PmsProductQueryParam;
import com.lhx.mall.dto.PmsProductResult;
import com.lhx.mall.mapper.PmsMemberPriceMapper;
import com.lhx.mall.mapper.PmsProductFullReductionMapper;
import com.lhx.mall.mapper.PmsProductLadderMapper;
import com.lhx.mall.mapper.PmsProductMapper;
import com.lhx.mall.model.*;
import com.lhx.mall.service.PmsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 商品管理service实现类
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductDao productDao;
    @Autowired
    private PmsMemberPriceDao memberPriceDao;
    @Autowired
    private PmsMemberPriceMapper memberPriceMapper;
    @Autowired
    private PmsProductLadderMapper productLadderMapper;
    @Autowired
    private PmsProductLadderDao productLadderDao;
    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);//商品状态为0代表改商品未被删除
        if(productQueryParam.getBrandId() != null){
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        }else if(productQueryParam.getKeyword() != null){
            criteria.andNameLike("%"+productQueryParam.getKeyword()+"%");
        }else if(productQueryParam.getProductCategoryId() != null){
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());
        }else if(productQueryParam.getProductSn() != null){
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        }else if(productQueryParam.getPublishStatus() != null){
            criteria.andPublishStatusEqualTo(productQueryParam.getPublishStatus());
        }else if(productQueryParam.getVerifyStatus() != null){
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        }
        return productMapper.selectByExample(productExample);
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setPublishStatus(publishStatus);
        PmsProductExample pmsProductExample = new PmsProductExample();
        pmsProductExample.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(pmsProduct,pmsProductExample);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setNewStatus(newStatus);
        PmsProductExample pmsProductExample = new PmsProductExample();
        pmsProductExample.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(pmsProduct,pmsProductExample);
    }

    @Override
    public int updateRecommandStatus(List<Long> ids, Integer recommandStatus) {
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setRecommandStatus(recommandStatus);
        PmsProductExample pmsProductExample = new PmsProductExample();
        pmsProductExample.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(pmsProduct,pmsProductExample);
    }

    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return productDao.getUpdateInfo(id);
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        int count =0;
        PmsProduct pmsProduct = productParam;
        pmsProduct.setId(id);
        productMapper.updateByPrimaryKey(pmsProduct);//修改商品信息
        //更新会员价格信息
        PmsMemberPriceExample memberPriceExample = new PmsMemberPriceExample();
        memberPriceExample.createCriteria().andProductIdEqualTo(id);
        memberPriceMapper.deleteByExample(memberPriceExample);
        relateAndInsertList(memberPriceDao,productParam.getMemberPriceList(),id);
        //更新阶梯价格
        PmsProductLadderExample productLadderExample = new PmsProductLadderExample();
        productLadderExample.createCriteria().andProductIdEqualTo(id);
        productLadderMapper.deleteByExample(productLadderExample);
        relateAndInsertList(productLadderDao,productParam.getProductLadderList(),id);
        //更新满减价格
        PmsProductFullReductionExample productFullReductionExample = new PmsProductFullReductionExample();
        productFullReductionExample.createCriteria().andProductIdEqualTo(id);
        productFullReductionMapper.deleteByExample(productFullReductionExample);
        relateAndInsertList(productFullReductionDao,productParam.getProductFullReductionList(),id);
        return count;
    }

    /**
     * 通用得到建立和插入关系表操作
     */
    public void relateAndInsertList(Object dao,List dataList,Long productId){
        try {
            if(CollectionUtils.isEmpty(dataList))return;
            for (Object data : dataList) {
                Method setId = data.getClass().getMethod("setId", Long.class);
                setId.invoke(data,(Long)null);
                Method setProductId = data.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(data,productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao,dataList);
        } catch (Exception e) {
            LOGGER.warn("创建产品出错",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    public void handSkuStockCode(List<PmsSkuStock> skuStockList){
        if(CollectionUtils.isEmpty(skuStockList))return;
        for(int i = 0;i < skuStockList.size();i++){
            PmsSkuStock skuStock = skuStockList.get(i);
            if(StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                StringBuilder builder = new StringBuilder();
                builder.append(dateFormat.format(new Date()));
                builder.append(String.format("%04d", skuStock.getId()));
                builder.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(builder.toString());
            }
        }
    }
}
