package com.akash.spring_junit_test_app.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.akash.spring_junit_test_app.user.enities.AuthorisationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.akash.spring_junit_test_app.user.enities.TestEntity;
import com.akash.spring_junit_test_app.user.enities.User;
import com.akash.spring_junit_test_app.user.services.UserServices;
import com.akash.spring_junit_test_app.user.util.PdfGenerator;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServices service;

    @Autowired
    private TestEntity testEntity;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return service.getUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = service.getUserById(id);
        if (user == null) {
            throw new NullPointerException();
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.OK);
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

    public void testMethod(User user, ArrayList<Integer> testList, TestEntity testEntity) {
        if (user.getId() == 2) {
            testList.add(user.getId());
        } else {
            testList.add(999);
        }
        testEntity.setTestList(testList);
    }

    @RequestMapping(value = "/service/getData", method = RequestMethod.GET)
    public ResponseEntity<?> getDataFromService() {
        ResponseEntity<String> response = null;
        String restServiceURL = "http://localhost:8245/admin/home";
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity request;
        try {
            httpHeaders.set("Token",
                    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaHZfYWRtIiwiZXhwIjoxNjgyMjIwMTQyLCJpYXQiOjE2ODIxODQxNDJ9.rbfWpqJy8JrUpboiExdwXz5ImLU1OauM-Du5ihnfkTU");
            request = new HttpEntity<>("", httpHeaders);
            response = restTemplate.exchange(restServiceURL, HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            String message = e.getMessage().toString();
            if (message.contains("\"message\":")) {
                message = message.substring(19);
                message = message.substring(0, message.length() - 3);
            }
            if (message.equals("Token has expired !!")) {
                String newToken = this.getTokenFromService().getBody().toString();
                newToken = newToken.substring(10);
                newToken = newToken.substring(0, newToken.length() - 2);
                httpHeaders.set("Token", newToken);
                request = new HttpEntity<>("", httpHeaders);
                response = restTemplate.exchange(restServiceURL, HttpMethod.GET, request, String.class);
            } else
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if (response != null && response.hasBody())
            return response;
        else
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/service/getToken", method = RequestMethod.GET)
    public ResponseEntity<?> getTokenFromService() {
        ResponseEntity<String> response = null;
        try {
            String restServiceURL = "http://localhost:8245/public/authenticate";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            AuthorisationRequest authorisationRequest = new AuthorisationRequest("mhv_adm", "1234");
            String requestJSON = new ObjectMapper().writeValueAsString(authorisationRequest);
            HttpEntity request = new HttpEntity<>(requestJSON, httpHeaders);
            response = restTemplate.exchange(restServiceURL, HttpMethod.POST, request, String.class);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Incorrect format of username or password !!", HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException.Forbidden e) {
            return new ResponseEntity<>("Invalid username or password !!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong on the server !!", HttpStatus.BAD_REQUEST);
        }
        if (response.hasBody())
            return response;
        else
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

}
