package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.constants.Constants;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class S3Repository {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    final Logger logger = LoggerFactory.getLogger(S3Repository.class);

    @Autowired
    public S3Repository(S3Client s3Client, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }

    private ArrayList<String> listFiles(String bucketName) {
        ArrayList<String> retval = new ArrayList<>();
        try {
            ListObjectsV2Request
                listObjectsRequest =
                ListObjectsV2Request.builder().bucket(bucketName).maxKeys(Constants.MAX_S3_KEYS).build();

            ListObjectsV2Response listObjectsResponse;
            do {
                listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);
                for(S3Object s3Object : listObjectsResponse.contents()){
                    final String file = s3Object.key();
                    if(!file.endsWith("/")){
                        retval.add(file);
                    }
                }
                listObjectsRequest = listObjectsRequest.toBuilder()
                    .continuationToken(listObjectsResponse.nextContinuationToken()).build();
            } while(listObjectsResponse.isTruncated());
        } catch (SdkClientException e) {
            logger.warn("Unable to list objects - {}", e.getMessage());
        }
        return retval;
    }

    public boolean downloadFile(String bucketName, String name, String filePath) {
        GetObjectRequest objectRequest = GetObjectRequest.builder().bucket(bucketName).key(name).build();
        ResponseBytes<GetObjectResponse> objectResponse = s3Client.getObjectAsBytes(objectRequest);

        try {
            OutputStream os = new FileOutputStream(filePath);
            os.write(objectResponse.asByteArray());
            os.close();
            return true;
        } catch (Exception e) {
            logger.warn("Unable to download {} from S3 - {}", name, e.getMessage());
            return false;
        }
    }

    private boolean uploadFile(String bucketName, @NonNull final MultipartFile multipartFile,
                               @NonNull final String key) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key)
                .contentType(multipartFile.getContentType())
                .contentLength(multipartFile.getSize())
                .build();
            s3Client.putObject(putObjectRequest,
                               RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize()));
            logger.info("Uploaded file to S3 - {}", key);
            return true;
        } catch (SdkClientException | IOException e) {
            logger.warn("Unable to upload to S3 - {}", e.getMessage());
            return false;
        }
    }

    private boolean uploadBytes(String bucketName, final byte @NonNull [] bytes, String contentType,
                                @NonNull final String key) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key)
                .contentType(contentType)
                .contentLength((long) bytes.length)
                .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(bytes));
            logger.info("Uploaded to S3 - {}", key);
            return true;
        } catch (SdkClientException e) {
            logger.warn("Unable to upload to S3 - {}", e.getMessage());
            return false;
        }
    }

    public boolean removeFile(String bucketName, String name) {
        try {
            DeleteObjectRequest deleteObjectRequest =
                DeleteObjectRequest.builder().bucket(bucketName).key(name).build();
            s3Client.deleteObject(deleteObjectRequest);
            logger.info("Deleted file from s3 on bucket {} with name {}", bucketName, name);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting file from s3 {}", e.getMessage());
            return false;
        }
    }

    public boolean removeFiles(String bucketName, List<String> names) {
        for(String name : names){
            DeleteObjectRequest deleteObjectRequest =
                DeleteObjectRequest.builder().bucket(bucketName).key(name).build();
            s3Client.deleteObject(deleteObjectRequest);
        }
        return true;
    }

    public boolean upload(@NonNull final String bucketName, @NonNull final MultipartFile multipartFile,
                          @NonNull final String key) {
        return uploadFile(bucketName, multipartFile, key);
    }

    public boolean upload(String bucketName, String name, final byte[] bytes, String contentType) {
        if(bucketName != null){
            return uploadBytes(bucketName, bytes, contentType, name);
        }
        return false;
    }

    public ArrayList<String> list(@NonNull final String bucketName) {
        return listFiles(bucketName);
    }

    public boolean remove(String bucketName, String name) {
        if(StringUtils.isNotBlank(bucketName) && StringUtils.isNotBlank(name)){
            try {
                return removeFile(bucketName, name);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean remove(String bucketName, List<String> names) {
        if(bucketName != null){
            return removeFiles(bucketName, names);
        }
        return false;
    }

    public String getPresignedUrl(@NonNull final String bucketName, @NonNull final String keyName, int expiryHours) {
        try {

            PresignedGetObjectRequest request = s3Presigner.presignGetObject(
                GetObjectPresignRequest.builder()
                    .getObjectRequest(GetObjectRequest.builder().bucket(bucketName).key(keyName).build())
                    .signatureDuration(Duration.ofHours(expiryHours))
                    .build());

            return request.url().toString();
        } catch (SdkClientException e) {
            logger.warn("Unable to generate presigned url for S3 object : {} - {}", keyName, e.getMessage());
            return Constants.EMPTY_STRING;
        }
    }

}

