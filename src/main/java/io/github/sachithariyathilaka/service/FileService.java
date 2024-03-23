package io.github.sachithariyathilaka.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Provides the file upload, download and delete operations.
 *
 * @author  Sachith Ariyathilaka
 * @version 1.0.0
 * @since   2024/03/17
 */
public interface FileService {
    String uploadFile(MultipartFile multipartFile);
    byte[] downloadFile(String fileName);
    String deleteFile(String fileName);
}
