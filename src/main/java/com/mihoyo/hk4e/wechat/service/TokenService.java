package com.mihoyo.hk4e.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.mihoyo.hk4e.wechat.dto.TokenDto;
import com.mihoyo.hk4e.wechat.tools.HttpsUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * token管理器
 */
@Component
public class TokenService {

    @Value("${corp.id}")
    private String corpId;
    @Value("${corp.secret}")
    private String corpSecret;
    @Value("${wx.url.access.token}")
    private String wxUrlAccessToken;

    private Logger logger = LoggerFactory.getLogger("TokenService");

    private TokenDto token;
    //如果发现token失效了 就启动线程异步去取 并标记tokenGetting 以免启动了很多线程
    private AtomicBoolean flag = new AtomicBoolean(false);

    /**
     * 获得缓存的token
     * @return null表示当前没有可用token 请稍后重试
     */
    public synchronized TokenDto getToken(){
        if(token != null && token.valid()){
            return token;
        }
        //启动线程去取
        if(flag.compareAndSet(false, true)){
            new TokenGettingThread().start();
        }
        return null;
    }

    public synchronized void setToken(String token, int expireIn){
       this.token = new TokenDto(token, System.currentTimeMillis() + expireIn * 1000L);
    }

    public void clearFlag(){
        this.flag.getAndSet(false);
    }

    class TokenGettingThread extends Thread{
        @Override
        public void run() {
            try{
                //去微信取token
                Map<String, String> params = new HashMap<>(2, 1f);
                params.put("corpid", corpId);
                params.put("corpsecret", corpSecret);
                HttpResponse response = HttpsUtils.doHttpsGet(wxUrlAccessToken, params);
                if(response == null){
                    logger.error("TokenGettingThread result:response is null");
                    return;
                }
                String result = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = JSONObject.parseObject(result);
                int errcode = jsonObject.getIntValue("errcode");
                if(errcode != 0){ //失败
                    logger.error("TokenGettingThread result:"+jsonObject.getString("errmsg"));
                    return;
                }
                String accessToken = jsonObject.getString("access_token");
                int expiresIn = jsonObject.getIntValue("expires_in");
                setToken(accessToken, expiresIn);
            }catch(Exception e){
                logger.error("", e);
            }finally {
                clearFlag();
            }
        }
    }

}
