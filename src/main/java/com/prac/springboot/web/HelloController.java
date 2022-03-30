package com.prac.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController             //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌, @RequestBody를 한방에 설정
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}