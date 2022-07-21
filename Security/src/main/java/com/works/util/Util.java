package com.works.util;

import java.util.Base64;
import java.util.Random;

public class Util {

    public static String md5( String pass ) {
        for (int i = 0; i < 3; i++) {
            pass = MD5Convert( pass );
        }
        return pass;
    }

    public static String MD5Convert(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String encoder(String data, int i) {
        byte[] dizi = null;
        Random rd = new Random();
        int ri = rd.nextInt(899) + 100;
        for (int j = 0; j < i; j++) {
            dizi = Base64.getEncoder().encode(data.getBytes());
            data = new String(dizi);
        }
        String sifrelenmis = new String(dizi) + md5("" + ri);
        return sifrelenmis;
    }

    public static String decoder(String data, int i) {
        try {
            byte[] dizi = null;
            data = data.substring(0, data.length() - 32);
            for (int j = 0; j < i; j++) {
                dizi = Base64.getDecoder().decode(data.getBytes());
                data = new String(dizi);
            }
            String cozulmus = new String(dizi);
            return cozulmus;
        }catch (Exception ex) {
            return null;
        }
    }

}
