package org.printmyavatar.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AwsS3Config {

    @Value("${amazon.s3.default-bucket}")
    private String AWS_S3_BUCKET;

    @Value("${amazon.aws.access-key-id}")
    private String AWS_ACCESS_KEY;

    @Value("${amazon.aws.access-key-secret}")
    private String AWS_SECRET_KEY;

    private AmazonS3 amazonS3;

    @PostConstruct
    private void onStart() {
        String s3Bucket = AWS_S3_BUCKET;
        String accessKey = AWS_ACCESS_KEY;
        String secretKey = AWS_SECRET_KEY;

        if ((accessKey != null) && (secretKey != null)) {
            AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
            amazonS3 = new AmazonS3Client(awsCredentials);
            amazonS3.createBucket(s3Bucket);
        }
    }

    public AmazonS3 getAmazonS3() {
        return amazonS3;
    }

    public String getBucket() {
        return AWS_S3_BUCKET;
    }

    public String getAccessKey() {
        return AWS_ACCESS_KEY;
    }

    public String getSecretKey() {
        return AWS_SECRET_KEY;
    }
}