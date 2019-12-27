package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.model.CmsPrefrenceArea;
import com.lhx.mall.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *商品优选专区管理controller
 */
@Api(tags = "CmsPrefrenceAreaController",description = "商品优选专区管理controller")
@RequestMapping("/prefrenceArea")
@Controller
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation(value = "获取所有商品优选")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsPrefrenceArea>> listAll(){
        return CommonResult.success(prefrenceAreaService.listAll());
    }
}
