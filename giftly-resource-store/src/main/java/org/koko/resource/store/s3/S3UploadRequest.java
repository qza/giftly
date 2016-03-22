package org.koko.resource.store.s3;

import com.amazonaws.event.ProgressListener;
import org.springframework.web.multipart.MultipartFile;

public class S3UploadRequest {

    private String bucketName;
    private String name;
    private MultipartFile multipartFile;
    private ProgressListener progressListener;

    public S3UploadRequest() {
    }

    public S3UploadRequest(String bucketName, String name, MultipartFile multipartFile) {
        this.bucketName = bucketName;
        this.name = name;
        this.multipartFile = multipartFile;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public ProgressListener getProgressListener() {
        return progressListener;
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }
}
