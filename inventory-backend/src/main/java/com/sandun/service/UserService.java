package com.sandun.service;

import com.sandun.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User login(String email,String password);

    User register(User newUser);

}
