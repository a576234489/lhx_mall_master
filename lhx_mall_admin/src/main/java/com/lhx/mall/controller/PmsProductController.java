package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonPage;
import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.dto.PmsProductParam;
import com.lhx.mall.dto.PmsProductQueryParam;
import com.lhx.mall.model.PmsProduct;
import com.lhx.mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "PmsProductController",description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService pmsProductService;

    @ApiOperation(value = "查询商品列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:product:read')")
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductQueryParam productQueryParam,
                                                        @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                                                        @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        List<PmsProduct> productList = pmsProductService.list(productQueryParam, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation(value = "批量上下架")
    @RequestMapping("/update/publishStatus")
    @ResponseBody
    public CommonResult updatePublishStatus(@RequestParam(value = "ids") List<Long> ids,
                                            @RequestParam(value = "publishStatus")Integer publishStatus){
        int count = pmsProductService.updatePublishStatus(ids, publishStatus);
        if(count > 0 ){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
    @ApiOperation(value = "批量设为/取消新品")
    @RequestMapping("/update/newStatus")
    @ResponseBody
    public CommonResult updatNewStatus(@RequestParam("ids")List<Long> ids,
                                       @RequestParam("newStatus")Integer newStatus){
        int count = pmsProductService.updateNewStatus(ids, newStatus);
        if(count > 0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
    @ApiOperation(value = "批量设为/取消推荐")
    @RequestMapping("/update/recommandStatus")
    @ResponseBody
    public CommonResult updateRecommandStatus(@RequestParam("ids")List<Long> ids,
                                              @RequestParam("recommandStatus")Integer status){
        int count = pmsProductService.updateRecommandStatus(ids, status);
        if(count > 0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }
    @ApiOperation(value = "更具商品id获取商品编辑信息")
    @RequestMapping("/updateInfo/{id}")
    @ResponseBody
    public CommonResult<PmsProduct> getUpdateInfo(@PathVariable Long id){
        return CommonResult.success(pmsProductService.getUpdateInfo(id));
    }
    @ApiOperation(value = "更新商品")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductParam productParam, BindResult bindResult){
        return null;
    }
}
