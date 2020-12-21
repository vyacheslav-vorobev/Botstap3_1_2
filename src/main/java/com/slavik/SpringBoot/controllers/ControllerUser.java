package com.slavik.SpringBoot.controllers;

import com.slavik.SpringBoot.model.User;
import com.slavik.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/user")
public class ControllerUser {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser(Authentication authentication, Model model){
        User user = userService.findByUserName(authentication.getName());
        model.addAttribute("user",user);
        model.addAttribute("userLogin", user.getLogin());
        String role;
        if(user.getRoles().size()>1){
            role="ROLE ADMIN";
        } else {
            role="ROLE USER";
        }
        model.addAttribute("role",role);
        return "user";
    }
}
