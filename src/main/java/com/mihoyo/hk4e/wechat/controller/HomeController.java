package com.mihoyo.hk4e.wechat.controller;

import com.mihoyo.hk4e.wechat.constants.MsgType;
import com.mihoyo.hk4e.wechat.dto.FileUploader;
import com.mihoyo.hk4e.wechat.dto.MessageSender;
import com.mihoyo.hk4e.wechat.service.FileService;
import com.mihoyo.hk4e.wechat.service.MessageService;
//import com.mihoyo.hk4e.wechat.service.SourceService;
import com.mihoyo.hk4e.wechat.tools.HttpsUtils;
import com.sun.deploy.net.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private FileService fileService;

    /**
     * just test if the service ok
     * @return
     */
    @RequestMapping("/test")
    public String index(){
//        SourceService sourceService = new SourceService();
//        String content = sourceService.getSource();

        FileUploader fileUploader = new FileUploader("file", "D:/PycharmProjects/mihoyo/test.py");
        String media_id = fileService.uploadFile(fileUploader);

        MessageSender ms = messageService.createOneMessageSender(MsgType.FILE);
        ms.addUser("xingyi.song");
        ms.setContent(media_id);
//        ms.setContent(content);
        messageService.sendMessage(ms);

        return "Just for test, the wechat platform simulator >_<";
    }
}