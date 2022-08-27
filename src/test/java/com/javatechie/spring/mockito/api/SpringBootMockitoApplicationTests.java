package com.javatechie.spring.mockito.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.javatechie.spring.mockito.api.dao.UserRepository;
import com.javatechie.spring.mockito.api.model.User;
import com.javatechie.spring.mockito.api.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMockitoApplicationTests {

	@Autowired // we are writing testcases for service layer
	private  UserService service; // injecting service

	 @MockBean  // we are mocking repository
	private UserRepository repository; // injecting repository

	@Test
	public void getUsersTest()
	{
    when(repository.findAll()).thenReturn(Stream
				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));

	assertEquals(2,service.getUsers().size());
	}

	@Test
	public void getUserbyAddressTest()
	{
		String address ="Kailaras";
		when(repository.findByAddress(address)).thenReturn(Stream
				.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));

		assertEquals(1,service.getUserbyAddress(address).size());
	}

	@Test
	public void addUserTest()
	{
		User user = new User(786, "deepak", 21 ,"india");
		when(repository.save(user)).thenReturn(user);

		assertEquals(user ,service.addUser(user));
	}


	@Test
	public void deleteUserTest()
	{
		User user = new User(786, "deepak", 21 ,"india");
		// return type is void so

		service.deleteUser(user);

		 // here we will check that our method is called or not

		verify(repository ,times(1)).delete(user);

	}




























//	@Autowired
//	private UserService service;
//
//	@MockBean
//	private UserRepository repository;
//
//	@Test
//	public void getUsersTest() {
//		when(repository.findAll()).thenReturn(Stream
//				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
//		assertEquals(2, service.getUsers().size());
//	}
//
//	@Test
//	public void getUserbyAddressTest() {
//		String address = "Bangalore";
//		when(repository.findByAddress(address))
//				.thenReturn(Stream.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
//		assertEquals(1, service.getUserbyAddress(address).size());
//	}
//
//	@Test
//	public void saveUserTest() {
//		User user = new User(999, "Pranya", 33, "Pune");
//		when(repository.save(user)).thenReturn(user);
//		assertEquals(user, service.addUser(user));
//	}
//
//	@Test
//	public void deleteUserTest() {
//		User user = new User(999, "Pranya", 33, "Pune");
//		service.deleteUser(user);
//		verify(repository, times(1)).delete(user);
//	}

}
