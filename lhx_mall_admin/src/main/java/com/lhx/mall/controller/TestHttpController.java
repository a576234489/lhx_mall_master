package com.lhx.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/test")
public class TestHttpController {

    @RequestMapping(value = "/http",method = RequestMethod.GET)
    public void testHttp(@RequestParam(value = "userId",required = false)String userId){
        System.out.println(userId);
        if(userId != null){
            System.out.println("发给指定用户："+userId);
        }else{
            System.out.println("发给所有人");
        }
    }

}
