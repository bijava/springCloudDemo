package com.devin.ribbonservice.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RibbonServiceControl {

    @RequestMapping("hello")
    public String hello(HttpServletRequest request) {
        return "Hello World Ribbon --"+request.getRequestURL();
    }
}
