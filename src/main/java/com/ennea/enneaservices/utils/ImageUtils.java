package com.ennea.enneaservices.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtils {

    public static byte[] imageToByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }

    public static int[] resizeWithAspectRatio(int originalWidth, int originalHeight, int newWidth, int newHeight) {
        double aspectRatio = (double) originalWidth / originalHeight;

        if(newWidth > 0){
            newHeight = (int) (newWidth / aspectRatio);
        } else if(newHeight > 0){
            newWidth = (int) (newHeight * aspectRatio);
        } else{
            return new int[]{originalWidth, originalHeight};
        }

        return new int[]{newWidth, newHeight};
    }

}
