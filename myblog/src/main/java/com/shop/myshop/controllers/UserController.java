package com.shop.myshop.controllers;

import com.shop.myshop.models.Role;
import com.shop.myshop.models.User;
import com.shop.myshop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String userList(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userslist";
    }

    @GetMapping("/user/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model) {

        if(!userRepository.existsById(id)){
            return "redirect:/user";
        }
        Optional<User> user =  userRepository.findById(id);
        ArrayList<User> list  = new ArrayList<>();
        user.ifPresent(list::add);
        model.addAttribute("user", list);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/user/{id}/edit")
    public String userPostUpdate(@PathVariable(value = "id") long id, @RequestParam String username,
                                 @RequestParam String password,
                                 Model model) {

        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/user";
    }

    @PostMapping("/user/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {

        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);

        return "redirect:/user";
    }
}
