package org.koko.resource.store.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class S3ResourceService {

    AmazonS3Client amazonS3Client;

    @Autowired
    public S3ResourceService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String createBucket(String name, String region) {
        Region regionInstance = StringUtils.isEmpty(region) ? amazonS3Client.getRegion() : Region.fromValue(region);
        if (!amazonS3Client.doesBucketExist(name)) {
            return amazonS3Client.createBucket(name, regionInstance).getName();
        }
        return amazonS3Client.getBucketLocation(name);
    }

    public List<S3ObjectSummary> list(String bucket) {
        return Arrays.asList(new S3ObjectSummary());
    }

    public PutObjectResult upload(S3UploadRequest s3UploadRequest) {
        return new PutObjectResult();
    }

    public S3Object download(String bucket, String key) {
        return new S3Object();
    }

    public DeleteObjectsResult delete(String bucket, String key) {
        return new DeleteObjectsResult(Arrays.asList(null));
    }

}
