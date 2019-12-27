package com.lhx.mall.controller;

import com.lhx.mall.common.api.CommonResult;
import com.lhx.mall.dto.UmsAdminLoginParam;
import com.lhx.mall.model.UmsAdmin;
import com.lhx.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "UmsAdminController",description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService umsAdminService;

//    @RequestBody可以将传过来的json数据装换为对象
    @ApiOperation(value = "登录以后返回token值")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result){
        String token = umsAdminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if(token == null){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("tokenHead",tokenHead);
        tokenMap.put("token",token);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前用户的登录信息")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal){
        String userName = principal.getName();
        UmsAdmin admin = umsAdminService.getAdminByUsername(userName);
        Map<String,Object> map = new HashMap<>();
        map.put("username",admin.getUsername());
        map.put("roles",new String[]{"Test"});
        map.put("icon",admin.getIcon());
        return CommonResult.success(map);
    }
}
