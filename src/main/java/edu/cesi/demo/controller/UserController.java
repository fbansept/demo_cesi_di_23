package edu.cesi.demo.controller;

import edu.cesi.demo.dao.UserDao;
import edu.cesi.demo.model.Product;
import edu.cesi.demo.model.Role;
import edu.cesi.demo.model.User;
import edu.cesi.demo.security.JwtUtils;
import edu.cesi.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PostMapping("/admin/add-user")
    public ResponseEntity<User> addUser(@RequestBody User user) {

        if(user.getId() != null) {

            Optional<User> userOptional = userDao.findById(user.getId());

            if(userOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            //On ne change que les informations suivantes :
            userOptional.get().setLogin(user.getLogin());

            //Si le JSON de l'utilisateur contient un mot de passe on le change.
            if(user.getPassword() != null) {
                userOptional.get().setPassword(passwordEncoder.encode(user.getPassword()));
            }

            userDao.save(userOptional.get());

            return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
        }

        //si l'utilisateur n'a pas de role, il est automatiquement client
        if(user.getRole() == null) {
            Role role = new Role();
            role.setId(3);
            user.setRole(role);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public void signIn(@RequestBody User user) {

        //TODO : validation login + mot de passe (alphanumric + caracteres speciaux ...)

        user.setId(null);

        Role role = new Role();
        role.setId(3);
        user.setRole(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

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
