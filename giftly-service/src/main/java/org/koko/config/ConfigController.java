package org.koko.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${key}")
    private String key;

    @RequestMapping("/key")
    public String getKey() {
        return key;
    }

}
