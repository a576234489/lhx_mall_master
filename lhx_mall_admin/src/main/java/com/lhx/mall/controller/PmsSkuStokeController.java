package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.model.PmsSkuStock;
import com.lhx.mall.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "PmsSkuStokeController",description = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStokeController {

    @Autowired
    private PmsSkuStockService skuStockService;

    @ApiOperation(value = "更具商品id及sku编码模糊查询sku库存")
    @RequestMapping(value = "/{pid}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsSkuStock>> getList(@RequestParam(value = "keyword",required = false)String keyword,
                                                   @PathVariable Long pid){
        List<PmsSkuStock> list = skuStockService.getList(pid, keyword);
        return CommonResult.success(list);
    }
    @ApiOperation(value = "批量修改sku库存")
    @RequestMapping("/update/{pid}")
    @ResponseBody
    public CommonResult update(@PathVariable Long pid,
                               @RequestBody List<PmsSkuStock> skuStockList){
        int count = skuStockService.update(pid, skuStockList);
        if(count > 0 ){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
}
