package com.mihoyo.hk4e.wechat.controller;

import com.mihoyo.hk4e.wechat.constants.MsgType;
import com.mihoyo.hk4e.wechat.dto.MessageSender;
import com.mihoyo.hk4e.wechat.entity.Token;
import com.mihoyo.hk4e.wechat.service.impl.MessageService;
import com.mihoyo.hk4e.wechat.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private MessageService messageService;
    /**
     * just test if the service ok
     * @return
     */
    @RequestMapping("/test")
    public String index(){
        MessageSender ms = new MessageSender(MsgType.TEXT);
        ms.addUser("xingyi.song");
        ms.setAgentId(1000020);
        ms.setContent("测试通讯1——哟啵请接收");
        messageService.sendMessage(ms);
        return "Just for test, the wechat platform simulator >_<";
    }
}
