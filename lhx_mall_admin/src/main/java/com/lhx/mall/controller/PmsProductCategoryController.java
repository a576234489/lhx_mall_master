package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.dto.PmsProductCategoryWithChildrenItem;
import com.lhx.mall.model.PmsProductCategory;
import com.lhx.mall.service.PmsProductCategoryService;
import com.lhx.mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "PmsProductCategoryController",description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryService productCategoryService;

    @ApiOperation(value = "查询所有商品的一级分类及其子类")
    @RequestMapping("list/withChildren")
    @ResponseBody
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren(){
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return CommonResult.success(list);
    }
}
