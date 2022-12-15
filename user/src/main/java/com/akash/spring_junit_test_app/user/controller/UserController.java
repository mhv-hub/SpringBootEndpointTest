package com.akash.spring_junit_test_app.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.akash.spring_junit_test_app.user.enities.User;
import com.akash.spring_junit_test_app.user.services.UserServices;
import com.akash.spring_junit_test_app.user.util.PdfGenerator;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServices service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return service.getUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id) {
        User user = service.getUserById(id);
        if (user == null) {
            throw new NullPointerException();
        } else {
            return user;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User user) {
        service.deleteUser(user);
    }

    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<User>> getUsersById(@PathVariable("id") int id) {
        User user = service.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(Optional.of(user));
            // return ResponseEntity.ok().body(Optional.of(user));
            // return ResponseEntity.status(200).body(Optional.of(user));
        }
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    @ResponseBody
    public String generatePDF() {
        System.out.println("Generate PDF method called");
        String filepath = "C:/Projects/Spring/JunitTest/JunitTest/user/pdf/savedReservation.pdf";
        PdfGenerator generate = new PdfGenerator();
        generate.generateItierary(filepath);
        return "PDF Generated Successfully";
    }
}
