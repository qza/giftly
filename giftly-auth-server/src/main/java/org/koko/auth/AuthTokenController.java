package org.koko.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthTokenController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

}