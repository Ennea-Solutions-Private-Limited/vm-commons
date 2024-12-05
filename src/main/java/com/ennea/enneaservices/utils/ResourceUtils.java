package com.ennea.enneaservices.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ResourceUtils {

    private static final Logger logger = LoggerFactory.getLogger(ResourceUtils.class);

    public BufferedImage loadImage(String imagePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(imagePath)) {
            if(inputStream == null){
                System.err.println("Image not found: " + imagePath);
                return null;
            }
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            logger.error("There was an error reading the image resource {}", e.getMessage());
            return null;
        }
    }

}
