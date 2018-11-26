package com.mihoyo.hk4e.wechat.constants;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * token管理器
 */
public class TokenManager {
    private static TokenManager instance = new TokenManager();
    public static TokenManager getInstance(){
        return instance;
    }

    //这里的token暂时只有这个 如果有多个 以后扩展为map
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
            //去微信取token

            String token = "";
            int expireIn = 1000;
            setToken(token, expireIn);
            clearFlag();
        }
    }

}
