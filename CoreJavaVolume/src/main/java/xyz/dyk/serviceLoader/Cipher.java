package xyz.dyk.serviceLoader;

/**
 * META-INF/service
 * META-INF/service/serviceLoader.Cipher
 * serviceLoader.impl.CaesarCipher
 */
public interface Cipher {
    byte[] encrypt(byte[] source, byte[] key);

    byte[] decrypt(byte[] source, byte[] key);

    int strength();
}
