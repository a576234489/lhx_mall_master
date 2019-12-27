package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.model.CmsSubject;
import com.lhx.mall.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品专题controller
 */
@Api(tags = "CmsSubjectController",description = "商品专题controller")
@RequestMapping("/subject")
@Controller
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService subjectService;

    @ApiOperation(value = "获取全部商品专题")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> listAll(){
        return CommonResult.success(subjectService.listAll());
    }
}
