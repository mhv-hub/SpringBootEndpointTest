package com.akash.spring_junit_test_app.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.akash.spring_junit_test_app.user.controller.UserController;
import com.akash.spring_junit_test_app.user.enities.TestEntity;
import com.akash.spring_junit_test_app.user.enities.User;
import com.akash.spring_junit_test_app.user.services.UserServices;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

//if package name is different in main and test then provide the name of main class like below :
//@SpringBootTest(classes=UserApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest
class UserApplicationTests {

	// @Mock
	// UserServices service;
	//
	// @InjectMocks
	// UserController userController;

	@MockBean
	UserServices service;

	// @Autowired
	// UserServices service;

	@Autowired
	UserController userController;

	@Autowired
	ApplicationContext context;

	User user;

	@BeforeEach
	public void initEntity() {
		user = context.getBean(User.class);
	}

	@Order(1)
	@Test
	@DisplayName("Test Add User Module")
	void testAddUser() {
		user.setId(1);
		user.setFirstName("Mahavir");
		user.setLastName("Ojha");
		user.setEmail("m@gmail.com");
		when(service.addUser(user)).thenReturn(user);
		User response = userController.addUser(user).getBody();
		verify(service).addUser(user);
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

	@Order(5)
	@Test
	void testDeleteUser() {
		doNothing().when(service).deleteUser(user);
		userController.deleteUser(user);
		verify(service).deleteUser(user);
	}

	@Order(4)
	@Test
	void testUpdateUser() {

	}

	@Order(3)
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

	@Order(2)
	@Test
	void testShowUserById() {
		user.setId(20);
		user.setFirstName("Mahavir");
		user.setLastName("Ojha");
		user.setEmail("m@gmail.com");
		when(service.getUserById(user.getId())).thenReturn(user);
		User response = userController.getUserById(user.getId()).getBody();
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

	@Order(7)
	@Test
	void testVoidMethods() {
		user.setId(2);
		user.setFirstName("Mahavir");
		user.setLastName("Ojha");
		user.setEmail("m@gmail.com");
		TestEntity testEntity = new TestEntity();
		testEntity.setTestList(new ArrayList<Integer>());
		userController.testMethod(user, new ArrayList<Integer>(), testEntity);
		assertEquals(user.getId(), testEntity.getTestList().get(0));
	}

	@Order(6)
	@ParameterizedTest
	@CsvSource({ "1", "2", "3", "4", "50", "27" })
	@Disabled
	public void testUsersExistInTheDatabase(int value) {
		List<User> users = service.getUsers();
		System.out.println(users);
		boolean flag = false;
		for (User user : users) {
			if (user.getId() == value)
				flag = true;
		}
		assertEquals(flag, true);
	}

	@Order(8)
	@ParameterizedTest
	@CsvSource({ "1,1", "2,2", "3,3", "4,4", "50,50", "27,27" })
	@Disabled
	public void testUsersExistInTheDatabaseType2(int value, int expected) {
		User user = userController.getUserById(value).getBody();
		assertEquals(expected, user.getId());
	}
}
