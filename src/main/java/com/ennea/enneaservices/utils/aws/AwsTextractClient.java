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
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextRequest;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextResponse;
import software.amazon.awssdk.services.textract.model.Document;
import software.amazon.awssdk.services.textract.model.S3Object;

import java.io.IOException;

@Component
public class AwsTextractClient {

    private final Logger logger = LoggerFactory.getLogger(AwsTextractClient.class);

    @Value("${cloud.aws.s3.ingestionTemplateBucket}")
    private String awsTextractBucket;

    private final S3Repository s3Repository;

    private final TextractClient textractClient;

    @Autowired
    public AwsTextractClient(S3Repository s3Repository, TextractClient textractClient) {
        this.s3Repository = s3Repository;
        this.textractClient = textractClient;
    }

    public DetectDocumentTextResponse extractTextFromImage(final MultipartFile image) throws IOException {
        byte[] imageBytes = image.getBytes();
        DetectDocumentTextRequest request = DetectDocumentTextRequest.builder()
            .document(Document.builder().bytes(SdkBytes.fromByteArray(imageBytes)).build()).build();
        return textractClient.detectDocumentText(request);
    }


    public DetectDocumentTextResponse extractTextFromS3Image(User user, final MultipartFile image) {
        String s3FileName = user.getId() + "-" + image.getOriginalFilename();
        boolean uploaded = s3Repository.upload(awsTextractBucket, image, s3FileName);
        if(!uploaded){
            logger.error("Shortage products image upload failed for user : {}", user.getId());
            return null;
        }

        DetectDocumentTextResponse textResponse;
        DetectDocumentTextRequest detectDocumentTextRequest = DetectDocumentTextRequest.builder()
            .document(Document.builder()
                          .s3Object(S3Object.builder().bucket(awsTextractBucket).name(s3FileName).build())
                          .build()).build();

        // Invoke the Detect operation.
        textResponse = textractClient.detectDocumentText(detectDocumentTextRequest);
        s3Repository.remove(awsTextractBucket, s3FileName);

        return textResponse;
    }
}
