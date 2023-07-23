package com.lexi.planet.Service.impl;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.lexi.planet.Service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

@Service
public class AWSS3Service implements StorageService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private AmazonS3 s3Client;
    private final long EXPIRATION_TIME = 86400 * 1000;

    public AWSS3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadMultipartFile(String bucketName, MultipartFile multipartFile) throws IOException {

        String s3Key = multipartFile.getOriginalFilename();
        String homeDir = System.getProperty("catalina.base") != null ? System.getProperty("catalina.base") : "/tmp";

        assert s3Key != null;
        File localFile = new File(homeDir + "/", s3Key);

        multipartFile.transferTo(localFile);

        s3Client.putObject(bucketName, s3Key, localFile);
        String s3Url = s3Client.getUrl(bucketName, s3Key).toString();

        // Make Sure the File delete after uploading
        // In Unix or Unix-like System, this piece of code can be removed
//        if(localFile.delete()) {
//            logger.info("AAAAAA File deleted successfully.");
//        } else {
//            logger.warn("========= Failed to delete the file.");
//        }

        return s3Url;
    }

    @Override
    public S3Object downloadFile(String bucketName, String key) {
        try {
            return s3Client.getObject(bucketName, key);
        } catch (AmazonS3Exception ae) {
            logger.error("====== The file located in s3://" + bucketName + "/" + key + "was not found!");
            return null;
        }
    }

    @Override
    public void deleteFile(String bucketName, String key) {
        try {
            s3Client.deleteObject(bucketName, key);
        } catch (AmazonS3Exception ae) {
            logger.error("====== The file located in s3://" + bucketName + "/" + key + "could not be delete");
        }
    }

    // This method is used to generate a pre-signed URL for a file in a specified bucket
    @Override
    public String generatePresignedURL(String bucketName, String objectKey, String httpMethodString) {
//        Date expiration = new Date();
//        long expTimeMillis = expiration.getTime();
//        expTimeMillis += EXPIRATION_TIME;

        // Set the Expiration Time
        Date expirationDateTime = new Date();
        expirationDateTime.setTime(System.currentTimeMillis() + EXPIRATION_TIME);

        // Generate the Pre-signed URL
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                .withMethod(HttpMethod.GET)
                .withExpiration(expirationDateTime);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
