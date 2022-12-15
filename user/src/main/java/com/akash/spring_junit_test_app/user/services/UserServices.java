package com.akash.spring_junit_test_app.user.services;

import java.util.List;

import com.akash.spring_junit_test_app.user.enities.User;

public interface UserServices {
    public User getUserById(int id);

    public List<User> getUsers();

    public User addUser(User user);

    public User updateUser(User user);

    public void deleteUser(User user);
}
