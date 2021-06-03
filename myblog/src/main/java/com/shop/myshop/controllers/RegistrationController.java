package com.shop.myshop.controllers;

import com.shop.myshop.models.Post;
import com.shop.myshop.models.Role;
import com.shop.myshop.models.User;
import com.shop.myshop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username, @RequestParam String password, Model model) {
        User userFromDB = userRepository.findByUsername(username);

        if (userFromDB != null)
            return "registration";

        User user = new User(username, password, true, Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }


}
