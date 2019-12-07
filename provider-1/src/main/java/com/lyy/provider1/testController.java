package com.lyy.provider1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider1")
public class testController {
    @GetMapping("/test")
    public String test(){
        return "你好，中国";
    }

}
