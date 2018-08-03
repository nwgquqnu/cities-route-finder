package com.crfinder.apigateway.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class SwaggerController {

    @ApiIgnore
    @GetMapping("/")
    public String redirect() {
        return "redirect:/swagger-ui.html";
    }
}
