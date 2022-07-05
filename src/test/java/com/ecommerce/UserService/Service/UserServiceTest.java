package com.ecommerce.UserService.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.UserService.entity.Address;
import com.ecommerce.UserService.entity.User;
import com.ecommerce.UserService.repository.UserRepository;
import com.ecommerce.UserService.service.UserService;

@SpringBootTest(classes = { UserServiceTest.class })
public class UserServiceTest {
	@Mock
	UserRepository userRepo;

	@InjectMocks
	UserService userService;

	List<User> user;

	@Test
	@Order(2)
	public void test_listCategories() {

		List<User> user = new ArrayList<User>();

		user.add(new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L));

		user.add(new User(2,"User2@gmail.com","User2","Pwd",new Address("line1","line2","city2",1235,"dist","state","Country"),402L));

		user.add(new User(3,"User3@gmail.com","User3","Pwd",new Address("line1","line2","city3",1236,"dist","state","Country"),403L));


		when(userRepo.findAll()).thenReturn(user);

		assertEquals(3, userService.listUsers().size());

	}

	@Test
	@Order(1)
	public void test_getSingleUser() {
		Address address = new Address();
		address.setCity(address.getCity());
		address.setCountry(address.getCountry());
		address.setDistrict(address.getDistrict());
		address.setLine1(address.getLine1());
		address.setLine2(address.getLine2());
		address.setState(address.getState());
		address.setZipcode(address.getZipcode());
	

		User user = new User();
		user.setAddress(address);
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());
		user.setPhone(user.getPhone());
		user.setUserId(user.getUserId());
		user.setUserName(user.getUserName());

		Integer userId = 1;

		when(userRepo.findById(userId)).thenReturn(Optional.ofNullable(user));

		assertEquals(user, userService.getSingleUser(userId));

	}

	@Test
	@Order(3)
	public void test_createUser() {
	User	user = new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L);

		when(userRepo.insert(user)).thenReturn(user);

		assertEquals(user, userService.createUser(user));
	}

	@Test
	@Order(4)
	public void test_editUser() {
		User	user = new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L);


		when(userRepo.save(user)).thenReturn(user);

		assertEquals(user, userService.editUser(user));
	}

	@Test
	@Order(5)
	public void test_deleteUser() {
		User	user = new User(1,"User1@gmail.com","User1","Pwd",new Address("line1","line2","city1",1234,"dist","state","Country"),401L);
		Integer userId = 1;
		 userService.deleteUser(userId);
		
		verify(userRepo,times(1)).deleteById(userId);
		
	}
}
