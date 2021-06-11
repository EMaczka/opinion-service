package com.yourpinion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YourpinionController {

    @GetMapping("/")
    public String rootView () {
        return "index";
    }
}
