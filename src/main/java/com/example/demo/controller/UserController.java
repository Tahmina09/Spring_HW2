package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = service.findUsers();


        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        service.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        service.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(Integer id, Model model){
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }


    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "redirect:/users";
    }
}
