package com.lexi.planet.controller;

import com.amazonaws.AmazonServiceException;
import com.lexi.planet.Service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class AWSS3Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService s3Service;

    @Value("${bucketName}")
    private String bucketName;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MULTIPART_FORM_DATA_VALUE)
    public String uploadMultipartFile(@RequestParam(value = "file") MultipartFile fileName) {
        try {
            String fileUrl = s3Service.uploadMultipartFile(bucketName, fileName);
            return fileUrl;
        } catch (AmazonServiceException | IOException e) {
            logger.error("================ error when uploading multipart file with error message = {}", e.getMessage());
            return "Error Uploading File";
        }
    }

    @RequestMapping(value = "/presignedurl", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> generatePresignedURL(@RequestParam(value = "key") String key){
        ResponseEntity<Object> responseEntity = null;
        String presignedURL = s3Service.generatePresignedURL(bucketName, key, HttpMethod.GET.toString());
        logger.info("The presignedURL is = {}", presignedURL );
        responseEntity = new ResponseEntity<>(presignedURL, HttpStatus.OK);
        return responseEntity;
    }


    @DeleteMapping("/delete/{bucketName}/{key}")
    public ResponseEntity<Void> deleteFile(@PathVariable String bucketName, @PathVariable String key) {
        s3Service.deleteFile(bucketName, key);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletefile")
    public ResponseEntity<String> deleteFile1(@RequestParam(value = "BucketName") String bucketName,
                                             @RequestParam(value = "key") String key) {
        try{
            s3Service.deleteFile(bucketName, key);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to Delete File");
        }

    }

}
