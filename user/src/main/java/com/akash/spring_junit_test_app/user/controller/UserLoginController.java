package com.akash.spring_junit_test_app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.akash.spring_junit_test_app.user.ExpHandeller.InternalServerError;
import com.akash.spring_junit_test_app.user.dto.LoginUser;
import com.akash.spring_junit_test_app.user.dto.RegisterUser;
import com.akash.spring_junit_test_app.user.services.RegisterUserService;
import com.akash.spring_junit_test_app.user.services.UserServiceImpl;
import com.akash.spring_junit_test_app.user.services.UserServices;

@Controller
public class UserLoginController {

    @Autowired
    private RegisterUserService service;

    @RequestMapping("/")
    public String showHome() {
        return "index";
    }

    @RequestMapping("/showLogin")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute("user") LoginUser user, Model model) {
        System.out.println(user);
        model.addAttribute("username", user.getUsername());
        return "showUser";
    }

    @RequestMapping("/showSignup")
    public String showSignup(Model model) {
        model.addAttribute("user", new RegisterUser());
        return "RegisterUser";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupController(@ModelAttribute("user") RegisterUser user, Model model) {
        System.out.println(user);
        // model.addAttribute("username", user.getEmail())
        RegisterUser user1 = service.addUser(user);
        if (user1 == null) {
            model.addAttribute("username", user);
            return "RegisterUser";
        }
        model.addAttribute("username", user1.getEmail());
        return "showUser";
    }
}
