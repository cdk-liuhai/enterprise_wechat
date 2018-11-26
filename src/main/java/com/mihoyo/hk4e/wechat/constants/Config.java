package com.mihoyo.hk4e.wechat.constants;

import com.mihoyo.hk4e.wechat.tools.qq.AesException;
import com.mihoyo.hk4e.wechat.tools.qq.WXBizMsgCrypt;

public class Config {

    /**
     * 不太清楚这些值哪些是动态的 哪些是配置的
     * 都先写死
     */
    private static String TOKEN = "QDG6eK";
    private static String CORP_ID = "wx5823bf96d3bd56c7";
    private static String ENCODING_AES_KEY = "jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C";


    public static WXBizMsgCrypt genWxBizMsgCrypt() throws AesException{
        return new WXBizMsgCrypt(TOKEN, ENCODING_AES_KEY, CORP_ID);
    }

}
