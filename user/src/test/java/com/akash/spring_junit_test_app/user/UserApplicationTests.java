package com.akash.spring_junit_test_app.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.akash.spring_junit_test_app.user.controller.UserController;
import com.akash.spring_junit_test_app.user.enities.User;
import com.akash.spring_junit_test_app.user.services.UserServices;

@SpringBootTest
class UserApplicationTests {

	@Mock
	UserServices service;

	@InjectMocks
	UserController userController;

	@Test
	void testAddUser() {
	}

	@Test
	void testDeleteUser() {
	}

	@Test
	void testUpdateUser() {
	}

	@Test
	void testShowUsers() {
		List<User> userList = new ArrayList<>();
		userList.add(new User(1, "A", "D", "a@gmail.com"));
		userList.add(new User(2, "B", "E", "b@gmail.com"));
		userList.add(new User(3, "C", "F", "c@gmail.com"));
		when(service.getUsers()).thenReturn(userList);
		List<User> responseList = userController.getAllUsers();
		verify(service).getUsers();
		assertNotNull(responseList);
		assertEquals(responseList.size(), userList.size());
		Iterator<User> itr = responseList.iterator();
		Iterator<User> inpItr = userList.iterator();
		while (itr.hasNext() && inpItr.hasNext()) {
			User inputUser = inpItr.next();
			User responseUser = itr.next();
			assertNotNull(responseUser);
			assertNotNull(responseUser.getId());
			assertNotNull(responseUser.getFirstName());
			assertNotNull(responseUser.getLastName());
			assertNotNull(responseUser.getEmail());
			assertEquals(responseUser.getId(), inputUser.getId());
			assertEquals(responseUser.getFirstName(), inputUser.getFirstName());
			assertEquals(responseUser.getLastName(), inputUser.getLastName());
			assertEquals(responseUser.getEmail(), inputUser.getEmail());
		}
	}

	@Test
	void testShowUserById() {
		User user = new User(20, "Mahavir", "Ojha", "mhv@gmail.com");
		when(service.getUserById(user.getId())).thenReturn(user);
		User response = userController.getUserById(user.getId());
		verify(service).getUserById(user.getId());
		assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getFirstName());
		assertNotNull(response.getLastName());
		assertNotNull(response.getEmail());
		assertEquals(response.getId(), user.getId());
		assertEquals(response.getFirstName(), user.getFirstName());
		assertEquals(response.getLastName(), user.getLastName());
		assertEquals(response.getEmail(), user.getEmail());
	}

}
