package com.aws.demo.service;

import com.aws.demo.dto.S3ReqDto;
import com.aws.demo.dto.S3ResDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@Service
public class AWSService {

    @Value("${file.path}")
    private String path;

    private final S3Service s3Service;

    public AWSService(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public S3ResDto addFileToS3(S3ReqDto s3ReqDto) {
        byte[] decodedBytes = Base64.getDecoder().decode(s3ReqDto.content());
        String key = path+s3ReqDto.fileName();
        s3Service.uploadFile(key, new ByteArrayInputStream(decodedBytes), decodedBytes.length, s3ReqDto.fileType());
        String signedUrl = s3Service.generatePresignedUrl(key, false);
        return new S3ResDto(key, signedUrl);
    }

    public S3ResDto uploadMultipartToS3(MultipartFile file) throws IOException {
        String key = path+file.getOriginalFilename();
        s3Service.uploadFile(key, file.getInputStream(), file.getSize(), file.getContentType());
        String signedUrl = s3Service.generatePresignedUrl(key, false);
        return new S3ResDto(key, signedUrl);
    }

    public void downloadFile(String file, HttpServletResponse response) throws IOException {
        String key = path+file;
        byte[] data = s3Service.downloadFile(key);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + key + "\"");
        response.setContentLength(data.length);
        OutputStream os = response.getOutputStream();
        os.write(data);
        os.flush();
    }

    public String deleteFile(String file) {
        s3Service.deleteFile(path+file);
        return "File deleted successfully";
    }

    public S3ResDto getSignedUrl(String file){
        String key = path+file;
        String url = s3Service.generatePresignedUrl(key, false);
        return new S3ResDto(key, url);
    }
}
