package edu.cesi.demo.controller;

import edu.cesi.demo.dao.UserDao;
import edu.cesi.demo.model.User;
import edu.cesi.demo.security.JwtUtils;
import edu.cesi.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){

        try {

            MyUserDetails userDetails = (MyUserDetails) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getLogin(),
                            user.getPassword()
                    )
            ).getPrincipal();

            return new ResponseEntity<>(jwtUtils.generateJwt(userDetails), HttpStatus.OK);

        } catch (Exception e) {
            //le login ou le mot de passe est erroné
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
