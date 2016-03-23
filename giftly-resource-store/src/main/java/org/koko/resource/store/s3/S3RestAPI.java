package org.koko.resource.store.s3;

import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/s3")
public class S3RestAPI {

    S3ResourceService s3ResourceService;

    @Autowired
    public S3RestAPI(S3ResourceService s3ResourceService) {
        this.s3ResourceService = s3ResourceService;
    }

    @RequestMapping("/list")
    public ResponseEntity<List<S3ObjectSummary>> list(@RequestParam(name = "bucket", defaultValue = "/", required = false) String bucket) {
        s3ResourceService.list(bucket);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<PutObjectResult> upload(@RequestParam("file") MultipartFile[] files) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam("key") String key) {
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<DeleteObjectsResult> delete(@RequestParam(name = "bucket", defaultValue = "/", required = false) String bucket,
                                                      @RequestParam("key") String key) {
        return ResponseEntity.ok(null);
    }

}
