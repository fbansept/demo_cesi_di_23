package edu.cesi.demo.controller;

import edu.cesi.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {

        User franck = new User();
        franck.setId(42);
        franck.setLogin("franck");

        return franck;
    }

    @GetMapping("/users")
    public ArrayList<User> getUsers() {

        User franck = new User();
        franck.setId(42);
        franck.setLogin("franck");

        User tom = new User();
        tom.setId(43);
        tom.setLogin("tom");

        ArrayList<User> listUser = new ArrayList<>();
        listUser.add(franck);
        listUser.add(tom);

        return listUser;
    }

}
