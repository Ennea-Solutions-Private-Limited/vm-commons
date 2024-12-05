package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.enums.ErrorEnum;
import com.ennea.enneaservices.exceptions.CustomEnneaExeption;
import com.ennea.enneaservices.model.Dto.ApiError;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class ApiConnectEncryptionUtils {
    private static final String ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";

    public static String aes128Encrypt(@NonNull final String plainText, @NonNull final String key) {
        try {
            log.info("Encrypting request body for axis api connect");
            final byte[] iv = new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,
                0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,
                0x72, 0x6F, 0x5A};
            final AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            final SecretKeySpec skeySpec = getSecretKeySpecFromHexString(ALGORITHM, key);
            final Cipher cipher;
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, paramSpec);
            final byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            final byte[] encryptedWithIV = copyIVAndCipher(encrypted, iv);
            return Base64.getEncoder().encodeToString(encryptedWithIV);
        } catch (final NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                       InvalidAlgorithmParameterException |
                       IllegalBlockSizeException | BadPaddingException | IOException e) {
            log.error("Unable to encrypt axis api connect request : {}", e.getMessage());
            final ApiError errorResponse =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.UNABLE_TO_ENCRYPT_REQUEST.getMessage(),
                             ErrorEnum.UNABLE_TO_ENCRYPT_REQUEST.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
    }

    public static String aes128Decrypt(@NonNull final String encryptedText, @NonNull final String key) {
        try {
            log.info("Decrypting response body from axis api connect");
            final SecretKeySpec skeySpec = getSecretKeySpecFromHexString(ALGORITHM, key);
            final byte[] encryptedIVandTextAsBytes = Base64.getDecoder().decode(encryptedText);
            final byte[] iv = Arrays.copyOf(encryptedIVandTextAsBytes, 16);
            final byte[] ciphertextByte = Arrays.copyOfRange(encryptedIVandTextAsBytes, 16,
                                                             encryptedIVandTextAsBytes.length);
            final Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv));
            final byte[] decryptedTextBytes = cipher.doFinal(ciphertextByte);
            return new String(decryptedTextBytes, StandardCharsets.UTF_8);
        } catch (final InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException |
                       InvalidKeyException |
                       IllegalBlockSizeException | BadPaddingException e) {
            log.error("Unable to decrypt axis api connect response : {}", e.getMessage());
            final ApiError errorResponse =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.UNABLE_TO_DECRYPT_RESPONSE.getMessage(),
                             ErrorEnum.UNABLE_TO_DECRYPT_RESPONSE.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
    }

    private static SecretKeySpec getSecretKeySpecFromHexString(final String algoCommonName, final String hexString) {
        final byte[] encodedBytes = hexStrToByteArray(hexString);
        return new SecretKeySpec(encodedBytes, algoCommonName);
    }

    private static byte[] hexStrToByteArray(final String hex) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(hex.length() / 2);
        for(int i = 0; i < hex.length(); i += 2){
            String output = hex.substring(i, i + 2);
            int decimal = Integer.parseInt(output, 16);
            baos.write(decimal);
        }
        return baos.toByteArray();
    }

    private static byte[] copyIVAndCipher(final byte[] encryptedText, final byte[] iv) throws IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(iv);
        os.write(encryptedText);
        return os.toByteArray();
    }
}
