package org.koko.resource.store.s3;

import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/rest/s3")
public class S3RestAPI {

    S3ResourceService s3ResourceService;

    @Autowired
    public S3RestAPI(S3ResourceService s3ResourceService) {
        this.s3ResourceService = s3ResourceService;
    }

    @RequestMapping(value = "/list", method = GET)
    public ResponseEntity<List<S3ObjectSummary>> list(@RequestParam(name = "bucket") String bucket) {
        List<S3ObjectSummary> s3ObjectSummaries = s3ResourceService.list(bucket);
        return ResponseEntity.ok(s3ObjectSummaries);
    }

    @RequestMapping(value = "/download", method = GET)
    public ResponseEntity<byte[]> download(
            @RequestParam("bucket") String bucket,
            @RequestParam("key") String key) throws IOException {
        S3Object s3Object = s3ResourceService.download(bucket, key);
        byte[] bytes = IOUtils.toByteArray(s3Object.getObjectContent());
        return ResponseEntity.ok(bytes);
    }

    @RequestMapping(value = "/upload", method = POST)
    public ResponseEntity<PutObjectResult> upload(S3UploadRequest s3UploadRequest) {
        PutObjectResult putObjectResult = s3ResourceService.upload(s3UploadRequest);
        return ResponseEntity.ok(putObjectResult);
    }

    @RequestMapping(value = "/delete", method = DELETE)
    public ResponseEntity<DeleteObjectsResult> delete(@RequestParam(name = "bucket") String bucket, @RequestParam("key") String key) {
        DeleteObjectsResult deleteObjectsResult = s3ResourceService.delete(bucket, key);
        return ResponseEntity.ok(deleteObjectsResult);
    }

}
