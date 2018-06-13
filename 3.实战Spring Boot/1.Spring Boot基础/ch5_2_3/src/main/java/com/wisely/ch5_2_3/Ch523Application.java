package com.wisely.ch5_2_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication   //1 .@SpringBootApplication是spring boot项目的核心注解，主要目的是开启自动配置
public class Ch523Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch523Application.class, args);
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    @ResponseBody
    String index() {
        return "Hello Spring Boot!";
    }
}
