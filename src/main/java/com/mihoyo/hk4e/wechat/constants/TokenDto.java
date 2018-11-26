package com.mihoyo.hk4e.wechat.constants;


public class TokenDto {
    private final String token;
    private final long expireTimeInMillis;

    public TokenDto(String token, long expireTimeInMillis){
        this.token = token;
        this.expireTimeInMillis = expireTimeInMillis;
    }

    public String getToken() {
        return token;
    }

    public long getExpireTimeInMillis() {
        return expireTimeInMillis;
    }


    public boolean valid(){
        return expireTimeInMillis > System.currentTimeMillis();
    }
}
