package com.avia.util;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

public class BaseUtil {

    /**
     * 计算签名值
     *
     * @param content   请求报文体
     * @param secretkey from_appkey 配置的私钥
     * @return
     */
    public static String calculateDigest(String content, String secretKey) {

        String text = content + secretKey;
        byte[] md5 = DigestUtils.md5(text);
        return Base64.encodeBase64String(md5);
    }

    public static void main(String[] args) {

        // {
        //   "companyCode": "STO",
        //   "mailNo": "77732232333223"
        // }

        Map<String,String> map = new HashMap<>();
        map.put("companyCode","CAKakHJIOTyuGko");
        map.put("mailNo","776368994226122");

        String content = JSON.toJSONString(map);
        System.out.println(content);
        String  secretKey ="7RrimcFVGUk2mK5uuCVZYXWVjbt4SpCv";
        String s = calculateDigest(content, secretKey);
        System.out.println(s);
    }

}
