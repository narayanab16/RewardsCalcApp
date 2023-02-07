package com.narayana.pointsapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @RequestMapping(value = "/")
    public @ResponseBody String error() {
        return "An error occurred, we will fix soon";
    }
}
