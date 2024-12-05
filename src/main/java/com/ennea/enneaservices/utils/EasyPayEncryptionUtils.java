package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.enums.ErrorEnum;
import com.ennea.enneaservices.exceptions.CustomEnneaExeption;
import com.ennea.enneaservices.model.Dto.ApiError;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class EasyPayEncryptionUtils {

    public static String getAESEncryptedString(@NonNull final String input, @NonNull final String key) {
        try {
            log.info("Encrypting axis easypay request");
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            final byte[] crypted = cipher.doFinal(input.getBytes());
            return new String(Base64.encodeBase64(crypted));
        } catch (final InvalidKeyException | IllegalBlockSizeException | BadPaddingException |
                       NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.error("Unable to encrypt axis easypay request");
            final ApiError errorResponse =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.UNABLE_TO_CREATE_PAYMENT_REQUEST.getMessage(),
                             ErrorEnum.UNABLE_TO_CREATE_PAYMENT_REQUEST.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
    }

    public static String getAESDecryptedString(@NonNull final String input, @NonNull final String key) {
        try {
            log.info("Decrypting axis easypay response");
            final SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            final byte[] output = cipher.doFinal(Base64.decodeBase64(input));
            return new String(output);
        } catch (final NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException
                       | BadPaddingException | InvalidKeyException e) {
            log.error("Unable to decrypt axis easypay response");
            final ApiError errorResponse =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.UNABLE_TO_CREATE_PAYMENT_REQUEST.getMessage(),
                             ErrorEnum.UNABLE_TO_CREATE_PAYMENT_REQUEST.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
    }

    public static String getSHA256EncryptedString(@NonNull final String input) {
        try {
            log.info("Creating axis easypay request checksum");
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            final byte[] byteData = md.digest();
            final StringBuilder hexString = new StringBuilder();
            for(byte byteDatum : byteData){
                final String hex = Integer.toHexString(0xff & byteDatum);
                if(hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (final NoSuchAlgorithmException e) {
            log.error("Unable to create axis easypay request checksum");
            final ApiError errorResponse =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.UNABLE_TO_CREATE_PAYMENT_REQUEST.getMessage(),
                             ErrorEnum.UNABLE_TO_CREATE_PAYMENT_REQUEST.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
    }
}
