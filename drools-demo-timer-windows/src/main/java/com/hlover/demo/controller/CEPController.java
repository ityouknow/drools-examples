package com.hlover.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cep")
public class CEPController {

    @GetMapping(path = "test01")
    public void test01 () {

    }

}
