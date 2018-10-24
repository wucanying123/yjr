package com.pt1002.util;



import java.security.MessageDigest;


public class MD5Util {
    private final static char[] HEX = "0123456789abcdef".toCharArray();

    private MD5Util() {

    }

    public static String getMD5(String data) {
        String digestData = "";
        byte[] digest=null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            digest=md5.digest(data.getBytes("iso8859-1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes2Hex(digest);
    }
    public static String bytes2Hex(byte[] bys) {
        char[] chs = new char[12];
        for (int i = 0, offset = 0; i < 12; i++) {
            chs[offset++] = HEX[bys[i] & 0x0f];
        }
        return new String(chs);
    }
    public static void main(String...args){
        System.out.println(getMD5("asd123"));
        System.out.println();
    }
}
