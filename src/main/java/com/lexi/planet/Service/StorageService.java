package com.lexi.planet.Service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    String uploadMultipartFile(String bucketName, MultipartFile multipartFile) throws IOException;

    S3Object downloadFile(String bucketName, String key);

    void deleteFile(String bucketName, String key);

    String generatePresignedURL(String bucketName, String objectKey, String httpMethodString);

    Boolean isObjectExist(String bucketName, String objectKey);
}
