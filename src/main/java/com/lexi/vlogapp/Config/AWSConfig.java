package com.lexi.vlogapp.Config;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AWSConfig {

    /*
    There are 3 ways to configure AWS S3 Client
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${awsRegion}")
    private String awsRegion;

    @Value("${aws.accessKeyId}")
    private String myAWSAccessKeyId;

    @Value("${aws.secretKey}")
    private String myAWSSecretKey;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getS3Client() {

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(myAWSAccessKeyId, myAWSSecretKey);

        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(awsRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        return s3Client;
    }



    private AmazonS3 gets3ClientByDefaultCredential(String awsRegion, String bucketName) {

        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(awsRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        return amazonS3;
    }

    /*
    DefaultAWSCredentialsProviderChain is a class provided by the AWS SDK. It's designed to look for
    and use AWS credentials. This class searches for credentials in several pre-defined places, such as
    environment variables, Java system properties, or AWS credentials configuration files, etc. The
    order of the search is as follows:

    // 1. Environment variables - AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
    // 2. Java system properties - aws.accessKeyId and aws.secretKey
    // 3. The default credential profiles file - typically located at ~/.aws/credentials
    // 4. Amazon ECS container credentials - provided by the Amazon EC2 container service
    // 5. Instance profile credentials - delivered through the Amazon EC2 metadata service

    Therefore, when you use DefaultAWSCredentialsProviderChain, you are actually using the credentials
    found at some location in this chain. You do not need to explicitly provide an access key ID and
    secret access key, as long as they can be found in one of these locations.

    This is also a secure practice because your credentials will not be hard-coded in your code. For
    applications running on Amazon EC2, you can use IAM roles to manage your credentials, so you do not
    need to store any credentials on your EC2 instances at all.
    */

    private AmazonS3 getS3ClientWithEnvironmentCredentials() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(awsRegion)
                .build();
        return s3Client;
    }

}
