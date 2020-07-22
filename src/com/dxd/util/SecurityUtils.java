package com.dxd.util;


import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtils {

    /**
     * md5鍔犲瘑
     *
     * @param value 瑕佸姞瀵嗙殑鍊?
     * @return md5鍔犲瘑鍚庣殑鍊?
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * 3娆d5鎿嶄綔
     * @param value
     * @return
     */
    public static String md5Hex3(String value) {
    	for (int i = 0; i < 3; i++) {
    		value = DigestUtils.md5Hex(value);
    	}
    	return value;
    }
    
    
    /**
     * sha256鍔犲瘑
     *
     * @param value 瑕佸姞瀵嗙殑鍊?
     * @return sha256鍔犲瘑鍚庣殑鍊?
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }

    public static String sha512Hex(String value) {
        return DigestUtils.sha512Hex(value);
    }
    
    public static void main(String[] args) {
    	System.out.println(SecurityUtils.md5Hex("123456"));
	}
}
