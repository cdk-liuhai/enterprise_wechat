package com.mihoyo.hk4e.wechat.service;

import com.mihoyo.hk4e.wechat.dto.TokenDto;
import com.mihoyo.hk4e.wechat.service.TokenService;
import com.mihoyo.hk4e.wechat.tools.qq.AesException;
import com.mihoyo.hk4e.wechat.tools.qq.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxCryptService {

    @Autowired
    private TokenService tokenService;
    @Value("${corp.id}")
    private String corpId;
    @Value("${encoding.aes.key}")
    private String encodingAesKey;


    public WXBizMsgCrypt genWxBizMsgCrypt() throws AesException {
        TokenDto token = tokenService.getToken();
        if(token == null){
            return null;
        }
        return new WXBizMsgCrypt(token.getToken(), encodingAesKey, corpId);
    }
}
