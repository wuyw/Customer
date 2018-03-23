package com.wxj.util;


public class Encrypt {

    public static class HashPassword {
        public String salt;
        public String password;
    }

    public static HashPassword encryptPassword(String plainPassword) {

        HashPassword result = new HashPassword();
        byte[] salt = Digests.generateSalt(8);
        result.salt = Encodes.encodeHex(salt);

        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, 1024);
        result.password = Encodes.encodeHex(hashPassword);
        return result;
    }

}
