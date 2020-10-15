package com.sandun.controller;

import com.sandun.model.User;
import com.sandun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> hello() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    User login(@RequestBody Map<String, String> usercredentials) {
        String email = usercredentials.get("email");
        String password = usercredentials.get("password");
        return userService.login(email, password);
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    User register(@RequestBody User newUser) {
        return userService.register(newUser);
    }

}
