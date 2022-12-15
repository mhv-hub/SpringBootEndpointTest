package com.akash.spring_junit_test_app.user.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.spring_junit_test_app.user.Repository.RegisterUserRepo;
import com.akash.spring_junit_test_app.user.dto.RegisterUser;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private RegisterUserRepo userRepository;

    @Override
    public RegisterUser getUserById(int id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            throw new NullPointerException();
        }

    }

    @Override
    public List<RegisterUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public RegisterUser addUser(RegisterUser user) {
        return null;
        // return userRepository.save(user);
    }

    @Override
    public RegisterUser updateUser(RegisterUser user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(RegisterUser user) {
        userRepository.delete(user);
    }

}
