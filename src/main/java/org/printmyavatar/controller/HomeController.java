package org.printmyavatar.controller;

import org.apache.commons.io.IOUtils;
import org.printmyavatar.config.AwsS3Config;
import org.printmyavatar.model.S3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.thymeleaf.util.StringUtils.isEmpty;

@Controller
public class HomeController {

    @Autowired
    private AwsS3Config awsS3Config;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file-upload") MultipartFile multipartFile,
                                   @RequestParam("num-of-images") Integer numberOfImages,
                                   Model model) throws IOException {

        if (multipartFile.getSize() > 1000000) {
            throw new RuntimeException("File upload exceeds 1MB");
        }
        String filename = multipartFile.getOriginalFilename();
        if (filename.endsWith(".jpg") || filename.endsWith(".png")
                || filename.endsWith(".jpeg") || filename.endsWith(".gif")) {
            throw new RuntimeException("File must be of type: [png, jpg, jpeg, gif]");
        }

        S3File s3File = new S3File(awsS3Config);
        String id = s3File.save(multipartFile);
        String imageLocation = String.format("/image/download/%s/%s", id, filename);

        List<String> images = new ArrayList<>();
        for (int i = 0; i < numberOfImages; i++) {
            images.add(imageLocation);
        }

        model.addAttribute("images", images);
        return "printpage";
    }

    @RequestMapping(value = "/image/download/{id}/{filename:.+}", method = RequestMethod.GET)
    public StreamingResponseBody handleFileDownload(@PathVariable("id") String id,
                                     @PathVariable("filename") String filename) throws IOException {
        if (isEmpty(filename) || filename.contains("/")) {
            System.out.println(filename);
            throw new RuntimeException("Invalid filename");
        }

        S3File s3File = new S3File(awsS3Config);
        URL url = s3File.getUrl(id, filename);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        return outputStream -> IOUtils.copy(connection.getInputStream(), outputStream);
    }

}
