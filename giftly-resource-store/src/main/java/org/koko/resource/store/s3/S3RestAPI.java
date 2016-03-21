package org.koko.resource.store.s3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/s3")
public class S3RestAPI {

    @RequestMapping("/list")
    public void list(@RequestParam(name = "bucket", defaultValue = "/", required = false) String bucket) {

    }

}
