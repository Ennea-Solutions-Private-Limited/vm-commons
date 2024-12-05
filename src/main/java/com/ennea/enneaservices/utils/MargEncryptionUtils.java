package com.ennea.enneaservices.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Component
public class MargEncryptionUtils {

    public static String decrypt(String strToDecrypt,
                                 String secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
        InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = secretKey.getBytes(StandardCharsets.UTF_8);
        int len = b.length;
        if(len > keyBytes.length){
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] results = cipher.doFinal(Base64.decodeBase64(strToDecrypt));
        return new String(results, StandardCharsets.UTF_8);
    }

    public static String decompress(String compressedText) throws DataFormatException {
        byte[] compressed = Base64.decodeBase64(compressedText);
        Inflater decompresser = new Inflater(true);
        decompresser.setInput(compressed);
        // Increasing from 32 MB to 64 MB to support marg all salesman invoice fetch
        byte[] result = new byte[67108864];
        int resultLength = decompresser.inflate(result);
        decompresser.end();
        return new String(result, 0, resultLength);
    }

    public static String sanitizeResponse(String response) throws IOException {
        String sanitizedResponse;
        byte[] decryptedResponseBytes = response.getBytes(StandardCharsets.UTF_8);

        BOMInputStream bomInputStream = BOMInputStream.builder().setInputStream(new ByteArrayInputStream(decryptedResponseBytes)).get();
        if(bomInputStream.hasBOM()){ // Remove Byte Order Marker (\uFEFF) from start of Response
            sanitizedResponse = new String(bomInputStream.readAllBytes(), StandardCharsets.UTF_8);
        } else{
            sanitizedResponse = response;
        }
        return sanitizedResponse;
    }

}
