package com.ennea.enneaservices.utils.aws;

import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.repository.S3Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.S3Object;
import software.amazon.awssdk.services.rekognition.model.TextDetection;
import software.amazon.awssdk.services.rekognition.model.TextTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AwsRekognitionClient {

    private final Logger logger = LoggerFactory.getLogger(AwsRekognitionClient.class);

    private final RekognitionClient rekognitionClient;

    private final S3Repository s3Repository;

    @Autowired
    public AwsRekognitionClient(RekognitionClient rekognitionClient, S3Repository s3Repository) {
        this.rekognitionClient = rekognitionClient;
        this.s3Repository = s3Repository;
    }

    @Value("${cloud.aws.s3.ingestionTemplateBucket}")
    private String awsTextractBucket;

    public List<String> getTextFromLineBlocks(MultipartFile imageFile) throws IOException {
        List<String> textList;
        byte[] bytesArray = imageFile.getBytes();
        Image image = Image.builder()
            .bytes(SdkBytes.fromByteArray(bytesArray))
            .build();

        textList = getExtractedListFromImage(image);

        return textList;
    }

    public List<String> getTextFromLineBlocksFromS3Image(User user, MultipartFile file) {
        List<String> textList;
        String s3FileName = user.getId() + "-" + file.getOriginalFilename();
        final boolean uploaded = s3Repository.upload(awsTextractBucket, file, s3FileName);
        if(!uploaded){
            logger.error("Shortage products image upload failed for user : {}", user.getId());
            return null;
        }
        Image s3Image =
            Image.builder().s3Object(S3Object.builder().bucket(awsTextractBucket).name(s3FileName).build()).build();
        textList = getExtractedListFromImage(s3Image);
        s3Repository.remove(awsTextractBucket, s3FileName);
        return textList;
    }

    public List<String> getExtractedListFromImage(Image image) {
        List<String> extractedTextList = new ArrayList<>();
        // Create DetectTextRequest
        DetectTextRequest request = DetectTextRequest.builder()
            .image(image)
            .build();

        // Call Amazon Rekognition to detect text
        DetectTextResponse response = rekognitionClient.detectText(request);

        // Extract text from line blocks
        for(TextDetection detection : response.textDetections()){
            if(detection.type().equals(TextTypes.LINE)){
                extractedTextList.add(detection.detectedText());
            }
        }
        return extractedTextList;
    }
}
