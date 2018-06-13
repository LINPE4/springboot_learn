package com.wisely.ch6_5.service;

import com.wisely.ch6_5.config.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    Hello hello;
    public  String sayHello() {
        return hello.getMsg();
    }
}
