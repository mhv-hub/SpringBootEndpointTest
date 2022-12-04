package com.akash.spring_junit_test_app.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.spring_junit_test_app.user.enities.User;

public interface UserRepo extends JpaRepository<User,Integer> {
    
}
