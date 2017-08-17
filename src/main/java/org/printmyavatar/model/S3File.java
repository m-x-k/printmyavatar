package org.printmyavatar.model;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.printmyavatar.config.AwsS3Config;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class S3File {

    private AwsS3Config awsS3Config;

    public S3File(AwsS3Config awsS3Config) {
        this.awsS3Config = awsS3Config;
    }

    public URL getUrl(String id, String name) throws MalformedURLException {
        return new URL("https://s3.amazonaws.com/" + awsS3Config.getBucket() + "/" + getActualFileName(id, name) + "?region=us-west-1");
    }

    private String getActualFileName(String id, String name) {
        return id + "/" + name;
    }

    public String save(MultipartFile file) {
        String id = "";
        if (awsS3Config.getAmazonS3() == null) {
            throw new RuntimeException("Could not save");
        }
        String bucket = awsS3Config.getBucket();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        try {
            id = UUID.randomUUID().toString();
            System.out.println("Saving file with UUID: " + id.toString());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, getActualFileName(id.toString(), file.getOriginalFilename()), file.getInputStream(), objectMetadata);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead); // public for all
            awsS3Config.getAmazonS3().putObject(putObjectRequest);// upload file
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void delete(UUID id, String name) {
        if (awsS3Config.getAmazonS3() == null) {
            throw new RuntimeException("Could not delete");
        }
        else {
            awsS3Config.getAmazonS3().deleteObject(awsS3Config.getBucket(), getActualFileName(id.toString(), name));
        }
    }

}