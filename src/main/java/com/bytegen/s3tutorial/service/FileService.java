package com.bytegen.s3tutorial.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @interface FileService
 * @description This interface use to handle file upload, download and delete operations
 *
 * @author Sachith Ariyathilaka
 * @version 1.0.0
 * @date 2024/03/17
 */
public interface FileService {
    String uploadFile(MultipartFile multipartFile);
    byte[] downloadFile(String fileName);
    String deleteFile(String fileName);
}
