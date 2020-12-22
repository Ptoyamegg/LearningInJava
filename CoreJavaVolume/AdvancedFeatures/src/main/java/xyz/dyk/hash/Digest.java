package xyz.dyk.hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * 数字签名
 */
public class Digest {
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        args = new String[]{"CoreJavaVolume/AdvancedFeatures/src/main/java/xyz/dyk/auth/AuthTest.policy"};
        String algname = args.length >= 2 ? args[1] : "SHA-1";
        MessageDigest alg = MessageDigest.getInstance(algname);
        byte[] input = Files.readAllBytes(Paths.get(args[0]));
        byte[] hash = alg.digest(input);
        String d = "";
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            if (v < 16) d += "0";
            d += Integer.toString(v, 16).toUpperCase() + " ";
        }
        System.out.println(d);
//        28 92 F6 5E BB 0B F5 99 06 7F 03 4E 02 FF 3D 9F 1C D8 50 1A
    }
}
