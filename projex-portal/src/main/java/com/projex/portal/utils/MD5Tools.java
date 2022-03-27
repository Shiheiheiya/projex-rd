package com.projex.portal.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密算法类
 */
public class MD5Tools {
    public static String getMD5(String password) throws NoSuchAlgorithmException {
        StringBuilder MD5 = new StringBuilder();

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = password.getBytes();
        byte[] digest = md5.digest(bytes);
        for (int b : digest) {
            int j = b;
            j = j & 0x000000ff;
            String s = Integer.toHexString(j);
            if (s.length() == 1){
                s = "0" + s;
            }
            MD5.append(s);
        }
        return MD5.toString();
    }
}