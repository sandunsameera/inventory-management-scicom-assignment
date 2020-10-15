package com.sandun.service.implementation;

import com.sandun.model.User;
import com.sandun.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private static final List<User> users = new ArrayList<>();

    static {
//   Innitialize users list

        User user1 = new User(1, "sandun", "sandunsameera25@gmail.com", "sandun");
        User user2 = new User(2, "sandun", "sanduni@gmail.com", "sandunsameera");

        users.add(user1);
        users.add(user2);
    }

    @Override
    public List<User> getAllUsers() {
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return users;
        }
    }


    @Override
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }
        return null;
    }

    @Override
    public User register(User newUser) {
        if (newUser.getEmail().isEmpty() || newUser.getPassword().isEmpty() || newUser.getUserName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        } else {
            users.add(newUser);
            return newUser;
        }
    }
}
