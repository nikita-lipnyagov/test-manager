package com.epam.lab.pet_project.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class StringCrypter {
    private static final Logger LOG = LoggerFactory.getLogger(StringCrypter.class);
    /**
     * Упрощенный конструктор. Создает StringCrypter с ключом DESSecretKey со значением по умолчанию (не рекомендуется)
     */
    public StringCrypter() {
        this(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    /**
     * Упрощенный конструктор. Создает StringCrypter с ключом
     * DESSecretKey (алгоритм шифрования DES) со значением key.
     * Ключ key должен иметь длину 8 байт
     */
    public StringCrypter(byte[] key) {
        try {
            updateSecretKey(new DESSecretKey(key));
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            LOG.error(ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public StringCrypter(SecretKey key) throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {
        updateSecretKey(key);
    }

    private void updateSecretKey(SecretKey key) throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {
        ecipher = Cipher.getInstance(key.getAlgorithm());
        dcipher = Cipher.getInstance(key.getAlgorithm());
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    public static class DESSecretKey implements SecretKey {

        private final byte[] key;

        /**
         * ключ должен иметь длину 8 байт
         */
        public DESSecretKey(byte[] key) {
            this.key = key;
        }

        @Override
        public String getAlgorithm() {
            return "DES";
        }

        @Override
        public String getFormat() {
            return "RAW";
        }

        @Override
        public byte[] getEncoded() {
            return key;
        }
    }

    private Cipher ecipher;
    private Cipher dcipher;

    /**
     * Функция шифрования
     *
     * @param str строка открытого текста
     * @return зашифрованная строка в формате Base64
     */
    public String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            LOG.debug("string was encrypted");
            return Base64.encodeBase64String(enc);
        } catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }

    /**
     * Функция дешифрования
     *
     * @param str зашифрованная строка в формате Base64
     * @return расшифрованная строка
     */
    public String decrypt(String str) {
        try {
            byte[] dec = Base64.decodeBase64(str);
            byte[] utf8 = dcipher.doFinal(dec);
            LOG.debug("string was decrypted");
            return new String(utf8, "UTF8");
        } catch (IllegalBlockSizeException | BadPaddingException | IOException ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }
}
