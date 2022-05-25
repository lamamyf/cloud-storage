package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class SimpleErrorController implements ErrorController {

    @GetMapping
    public String getErrorPage(Model model){
        model.addAttribute("unknownError", "Something went wrong");
        return "result";
    }
}
