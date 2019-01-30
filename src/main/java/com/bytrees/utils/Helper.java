package com.bytrees.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {
	/**
	 * 计算字符串md5
	 * @param source
	 * @return
	 */
    public static String md5(String source) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] sourceByteArr = source.getBytes();
		messageDigest.update(sourceByteArr);
		byte[] resultByteArr = messageDigest.digest();
    	return new String(byteArrayToHex(resultByteArr)).toLowerCase();
    }

    private static char[] byteArrayToHex(byte[] sourceByteArr) {
    	char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
    	char[] resultCharArr = new char[sourceByteArr.length * 2];
    	int index = 0;
        for (byte b : sourceByteArr) {
        	resultCharArr[index++] = hexDigits[b>>> 4 & 0xf];
        	resultCharArr[index++] = hexDigits[b& 0xf];     
	    }
        return resultCharArr;
    }
}
