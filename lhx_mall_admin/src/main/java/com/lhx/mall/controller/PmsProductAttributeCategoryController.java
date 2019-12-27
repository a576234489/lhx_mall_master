package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonPage;
import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.model.PmsProductAttributeCategory;
import com.lhx.mall.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品属性分类controller
 */
@Api(tags = "PmsProductAttributeCategotyController",description = "商品属性分类")
@RequestMapping("productAttribute/category")
@Controller
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                                         @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){
        return CommonResult.success(CommonPage.restPage(productAttributeCategoryService.getList(pageNum,pageSize)));
    }
}
