package com.slavik.SpringBoot.controllers;

import com.slavik.SpringBoot.service.RoleService;
import com.slavik.SpringBoot.model.Role;
import com.slavik.SpringBoot.model.User;
import com.slavik.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@Controller
@RequestMapping("/admin")
public class ControllerAdmin {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewsUsers(Authentication authentication, Model model){
        List<User> userList = userService.listUsers();
        User user = userService.findByUserName(authentication.getName());
        userList.remove(user);
        model.addAttribute("users", userList);
        model.addAttribute("user1", user);
        model.addAttribute("user", new User());
        for (User user1: userList){
            model.addAttribute(("password"+user1.getId()), user1.getPassword());
        }
        return "allUsers";
    }
    @GetMapping("/{id}")
    public String getOneUser(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }
    @PostMapping("/new")
    public String create(@ModelAttribute("login") String login, @ModelAttribute("password") String password,
                         @ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName,
                         @ModelAttribute("age") int age, @ModelAttribute("growth") int growth,
                         @RequestParam(value = "role")String role){
        System.out.println(role);
        User user = new User();
        user.setLogin(login);
        user.setPassword(encoder.encode(password));
        user.setPassword(password);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getOne(2L));
        if(role.equals("ADMIN")){
            roles.add(roleService.getOne(1L));
        }
        user.setRoles(roles);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setGrowth(growth);
        userService.addUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id")Long id){
        model.addAttribute("user",userService.getUser(id));
        return "edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam(value = "role")String role){
        Set<Role> roles = new HashSet<>();
        if(role.equals("ADMIN")) {
            roles.add(roleService.getOne(1L));
            roles.add(roleService.getOne(2L));
            user.setRoles(roles);
        } else {
            roles.add(roleService.getOne(2L));
        }
        user.setRoles(roles);
        userService.upDate(id,user);
        return "redirect:/admin";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        userService.remove(id);
        return "redirect:/admin";
    }
}


