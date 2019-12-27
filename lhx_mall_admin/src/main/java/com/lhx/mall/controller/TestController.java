package com.lhx.mall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/Hello")
    public String hello(){
        return "hello";
    }
}
