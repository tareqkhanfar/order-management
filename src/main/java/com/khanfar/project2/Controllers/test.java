package com.khanfar.project2.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("print")
    public String print() {
        return "test";
    }
}
