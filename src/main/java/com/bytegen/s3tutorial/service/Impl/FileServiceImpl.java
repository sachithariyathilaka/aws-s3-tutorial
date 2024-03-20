package com.bytegen.s3tutorial.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.bytegen.s3tutorial.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @class       FileServiceImpl
 * @description Service layer of the file storage
 *
 * @author      Sachith Ariyathilaka
 * @version     1.0.0
 * @date        2024/03/17
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    private final AmazonS3 amazonS3;

    /**
     * @constructor FileServiceImpl
     * @description Argument constructor for class FileServiceImpl
     *
     * @param       amazonS3 the amazon s3
     */
    public FileServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    /**
     * @method      uploadFile
     * @description Provides the upload file functionality
     *
     * @param       multipartFile the multipart file
     *
     * @return      the file name
     */
    @Override
    public String uploadFile(MultipartFile multipartFile) {
        File file = convert(multipartFile);
        String fileName = file.getName() + "-" + System.currentTimeMillis();

        amazonS3.putObject(bucketName, fileName, file);
        file.delete();

        return fileName;
    }

    /**
     * @method      downloadFile
     * @description Provides the download file functionality
     *
     * @param       fileName the file name
     *
     * @return      the file
     */
    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException exception) {
            throw new RuntimeException("Error occurred while download the file: " + exception);
        }
    }

    /**
     * @method      uploadFile
     * @description Provides the upload file functionality
     *
     * @param       fileName the file name
     *
     * @return      the file name
     */
    @Override
    public String deleteFile(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
        return fileName;
    }

    /**
     * @method      convert
     * @description Convert MultipartFile into File
     *
     * @param       multipartFile the multipart file
     *
     * @return      the file
     */
    private File convert(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException exception) {
            throw new RuntimeException("Error occurred while convert multipart file into file: " + exception);
        }

        return file;
    }
}
