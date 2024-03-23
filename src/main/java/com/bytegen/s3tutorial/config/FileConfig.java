package com.bytegen.s3tutorial.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Set up basic configurations for Amazon S3 service.
 *
 * @author  Sachith Ariyathilaka
 * @version 1.0.0
 * @since   2024/03/17
 */
@Configuration
public class FileConfig {

    @Value("${aws.s3.access.key}")
    private String accessKey;

    @Value("${aws.s3.secret.key}")
    private String secretKey;

    @Value("${aws.s3.region.name}")
    private String regionName;

    /**
     * Initiate bean for Amazon S3 service.
     *
     * @return  the amazon s3
     */
    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        return AmazonS3ClientBuilder.standard().withCredentials(awsStaticCredentialsProvider).withRegion(regionName).build();
    }
}
