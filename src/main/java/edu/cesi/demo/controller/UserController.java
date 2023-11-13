package edu.cesi.demo.controller;

import edu.cesi.demo.dao.UserDao;
import edu.cesi.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id){
        return userDao.findById(id).orElse(null);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> listUser = userDao.findAll();
        return listUser;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id){
        userDao.deleteById(id);
    }

    @PostMapping("/user")
    public void editUser(@RequestBody User user) {
        userDao.save(user);
    }
}
