package com.sandun.service.implementation;

import com.sandun.model.User;
import com.sandun.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return users;
    }


    @Override
    public User login(String email, String password) {
        Optional<User> loginUser = users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password))
                .findAny();

        if (loginUser.isPresent()) {
            return loginUser.get();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public User register(User newUser) {
        if (newUser.getEmail().isEmpty() || newUser.getPassword().isEmpty() || newUser.getUserName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            if (newUser.getPassword().length() <= 5) {
                throw new ResponseStatusException(HttpStatus.LENGTH_REQUIRED);
            } else {
                users.add(newUser);
                return newUser;
            }
        }
    }
}
