package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonPage;
import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.model.PmsBrand;
import com.lhx.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "PmsBrandController",description = "品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    @ApiOperation(value = "查询品牌列表")
    @RequestMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> getList(@RequestParam(value = "keyword",required = false) String keyword,
                                                      @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        List<PmsBrand> list = brandService.listBrand(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
