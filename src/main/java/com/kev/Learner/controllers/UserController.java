package com.kev.Learner.controllers;

import com.kev.Learner.entities.User;
import com.kev.Learner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUser( @PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/users/add")
    public User createUser(@RequestBody User user){
        User newuser = new User();
        newuser.setFirstname(user.getFirstname());
        newuser.setLastname(user.getLastname());
        newuser.setEmail(user.getEmail());
        userRepository.save(newuser);

        return newuser;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/users/edit/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User userDb = userRepository.findByUserId(id);
        userDb.setFirstname(user.getFirstname());
        userDb.setLastname(user.getLastname());
        userDb.setEmail(user.getEmail());
        userRepository.save(userDb);

        return userDb;
    }
}
