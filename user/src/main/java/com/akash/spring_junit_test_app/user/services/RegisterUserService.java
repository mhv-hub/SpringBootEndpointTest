package com.akash.spring_junit_test_app.user.services;

import java.util.List;

import com.akash.spring_junit_test_app.user.dto.RegisterUser;

public interface RegisterUserService {

    public RegisterUser getUserById(int id);

    public List<RegisterUser> getUsers();

    public RegisterUser addUser(RegisterUser user);

    public RegisterUser updateUser(RegisterUser user);

    public void deleteUser(RegisterUser user);
}
