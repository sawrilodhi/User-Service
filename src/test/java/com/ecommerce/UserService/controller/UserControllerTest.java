package com.ecommerce.UserService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.UserService.entity.Address;
import com.ecommerce.UserService.entity.User;
import com.ecommerce.UserService.service.UserService;

@SpringBootTest(classes = { UserControllerTest.class })
public class UserControllerTest {

	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	User user;
	
	@Test
	@Order(1)
	public void test_saveUser() {

		user = new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L);

		when(userService.createUser(user)).thenReturn(user);

		ResponseEntity<User> res = userController.saveUser(user);

		assertEquals(HttpStatus.CREATED, res.getStatusCode());

		assertEquals(user, res.getBody());

	}
	
	@Test
	@Order(2)
	public void test_listAllUsers() {

		List<User> user = new ArrayList<User>();

		user.add(new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L));

		user.add(new User(2,"User2@gmail.com","User2","Pwd",new Address("line1","line2","city2",1235,"dist","state","Country"),402L));

		user.add(new User(3,"User3@gmail.com","User3","Pwd",new Address("line1","line2","city3",1236,"dist","state","Country"),403L));

		when( userService.listUsers()).thenReturn(user);

		ResponseEntity<List<User>> res = userController.listAllUsers();

		assertEquals(HttpStatus.FOUND, res.getStatusCode());

		assertEquals(3, res.getBody().size());
	}

	
	@Test

	@Order(3)

	public void test_updateUser() {

		user = new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L);


		Integer userId = 1;

		when(userService.getSingleUser(userId)).thenReturn(user);

		when(userService.editUser(user)).thenReturn(user);

		ResponseEntity<User> res = userController.updateUser(userId, user);

		assertEquals(HttpStatus.OK, res.getStatusCode());

		assertEquals(1, res.getBody().getUserId());

		assertEquals("User1", res.getBody().getUserName());

	}
	
	@Test
	@Order(4)
	public void test_getUserById() {
		user = new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L);

		Integer userId = 1;

		when(userService.getSingleUser(userId)).thenReturn(user);
		ResponseEntity<User> res = userController.getUserById(userId);

		assertEquals(HttpStatus.FOUND, res.getStatusCode());

		assertEquals(userId, res.getBody().getUserId());

	}
	@Test
	@Order(5)
	public void test_deleteUser() {
		user = new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L);

		Integer userId = 1;

		when(userService.getSingleUser(userId)).thenReturn(user);
		ResponseEntity<User> res = userController.deleteUser(userId);

		assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	
}
