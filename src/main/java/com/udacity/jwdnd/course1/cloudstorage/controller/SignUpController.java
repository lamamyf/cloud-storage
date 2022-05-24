package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.dto.SignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @PostMapping
    public String register(SignUpForm signUpForm, RedirectAttributes redirectAttributes){
        userService.register(signUpForm.username(), signUpForm.password(), signUpForm.firstName(), signUpForm.lastName());
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/login";
    }

    @GetMapping
    public String getSignUpPage(SignUpForm signUpForm){
        return "signup";
    }
}
