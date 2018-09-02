package com.christiantlm.web;

import com.christiantlm.model.User;
import com.christiantlm.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dot.zhou on 2018/9/1.
 */
@Controller
public class DemoResource {

    @Autowired
    private DemoService demoService;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>(demoService.hello(), HttpStatus.OK);
    }

    @GetMapping(value = "users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(DemoService.globalUsers, HttpStatus.OK);
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<List<User>> getUsersById(@PathVariable("id") String userId) {
        return new ResponseEntity<>(demoService.getUsers(userId), HttpStatus.OK);
    }

    @PostMapping(value = "users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        demoService.addUser(user);
        return new ResponseEntity<>("create success", HttpStatus.OK);
    }

    @PutMapping(value = "users")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        demoService.updateUser(user);
        return new ResponseEntity<>("update success", HttpStatus.OK);
    }

    @DeleteMapping(value = "users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        demoService.deleteUser(userId);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

}

