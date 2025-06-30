package com.aws.demo.controller;

import com.aws.demo.dto.S3ReqDto;
import com.aws.demo.dto.S3ResDto;
import com.aws.demo.service.AWSService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class AWSController {

    private final AWSService awsService;

    public AWSController(AWSService awsService) {
        this.awsService = awsService;
    }

    @PostMapping
    public S3ResDto addFileToS3(@RequestBody S3ReqDto s3ReqDto){
        return awsService.addFileToS3(s3ReqDto);
    }

    @PostMapping("/upload/multipart")
    public S3ResDto upload(@RequestParam("file") MultipartFile file) throws IOException {
        return awsService.uploadMultipartToS3(file);
    }

    @GetMapping("/download/{file}")
    public void download(@PathVariable String file, HttpServletResponse response) throws IOException {
        awsService.downloadFile(file, response);
    }

    @DeleteMapping("/{file}")
    public String delete(@PathVariable String file) {
        return awsService.deleteFile(file);
    }

    @GetMapping("/{file}")
    public S3ResDto getUrl(@PathVariable String file){
        return awsService.getSignedUrl(file);
    }
}
