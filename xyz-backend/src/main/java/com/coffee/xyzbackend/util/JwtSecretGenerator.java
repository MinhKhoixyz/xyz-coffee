package com.coffee.xyzbackend.util;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[64];
        secureRandom.nextBytes(keyBytes);
        String enterpriseSecretKey = Base64.getEncoder().encodeToString(keyBytes);

        System.out.println("🔑 JWT Secret Enterprise:");
        System.out.println("------------------------------------------------------------------");
        System.out.println(enterpriseSecretKey);
        System.out.println("------------------------------------------------------------------");
    }
}
