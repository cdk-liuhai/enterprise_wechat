package com.mihoyo.hk4e.wechat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    /**
     * just test if the service ok
     * @return
     */
    @RequestMapping("/test")
    public String index(){
        return "Just for test, the wechat platform simulator >_<";
    }
}
