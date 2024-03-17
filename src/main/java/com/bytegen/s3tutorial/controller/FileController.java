package com.bytegen.s3tutorial.controller;

import com.bytegen.s3tutorial.resource.APIResponse;
import com.bytegen.s3tutorial.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @class FileController
 * @description This class is the controller layer of the file storage
 *
 * @author Sachith Ariyathilaka
 * @version 1.0.0
 * @date 2024/03/17
 */
@RequestMapping("/api/file")
@RestController
public class FileController {
    private final FileService fileService;

    /**
     * @constructor FileController
     * @description This is the argument constructor for class FileController
     *
     * @param fileService the file service
     */
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * @method uploadFile
     * @description This method used to create endpoint for upload file
     *
     * @param multipartFile the multipart file
     *
     * @return the api response
     */
    @PostMapping("/upload")
    public ResponseEntity<APIResponse<String>> uploadFile(@RequestParam(value = "file") MultipartFile multipartFile) {
        String fileName = fileService.uploadFile(multipartFile);
        APIResponse<String> apiResponse = new APIResponse<>(200, "File uploaded successfully!", fileName);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * @method downloadFile
     * @description This method used to create endpoint for download file
     *
     * @param fileName the file name
     *
     * @return the api response
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<APIResponse<byte[]>> downloadFile(@PathVariable String fileName) {
        byte[] file = fileService.downloadFile(fileName);
        APIResponse<byte[]> apiResponse = new APIResponse<>(200, "File downloaded successfully!", file);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * @method deleteFile
     * @description This method used to create endpoint for delete file
     *
     * @param fileName the file name
     *
     * @return the api response
     */
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<APIResponse<String>> deleteFile(@PathVariable String fileName) {
        fileName = fileService.deleteFile(fileName);
        APIResponse<String> apiResponse = new APIResponse<>(200, "File deleted successfully!", fileName);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
