package com.example.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(allowCredentials = "true", origins = "{http://localhost:3000,http://localhost:8181}")
public class TestController {

    @GetMapping("/hello-world/**")
    String helloWorld(@CookieValue("jwt") String jwtCookie) {
        System.out.println(jwtCookie);
        return "hello world";
    }
}
