package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonPage;
import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.model.PmsProductAttribute;
import com.lhx.mall.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "PmsProductAttributeController",description = "商品属性管理")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService productAttributeService;

    @ApiOperation(value = "更具分类id以及类型查询商品参数列表或者规格列表")
    @ApiImplicitParams(@ApiImplicitParam(name = "type",value = "0代表规格,1代表参数",required = true,paramType = "query",dataType = "integer"))
    @RequestMapping(value = "/list/{cid}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cid,
                                                                      @RequestParam(value = "type")Integer type,
                                                                      @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                      @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        List<PmsProductAttribute> list = productAttributeService.getList(cid, type, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
