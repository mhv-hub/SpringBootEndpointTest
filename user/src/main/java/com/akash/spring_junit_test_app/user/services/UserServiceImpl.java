package com.akash.spring_junit_test_app.user.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.spring_junit_test_app.user.Repository.UserRepo;
import com.akash.spring_junit_test_app.user.enities.User;


@Service
public class UserServiceImpl implements UserServices{

    @Autowired
    private UserRepo userRepository;

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);        
    }

    
}
