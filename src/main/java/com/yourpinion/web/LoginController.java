package com.yourpinion.web;

import com.yourpinion.user.User;
import com.yourpinion.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(ModelMap modelMap) {
        modelMap.put("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(User user) {
        User savedUser = userService.save(user);
        return "redirect:/login";
    }
}
