package com.user.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.bean.UserBean;
import com.user.controller.UserController;
import com.user.entity.User;
import com.user.exception.NoUsersFoundException;
import com.user.exception.UserDeleteException;
import com.user.exception.UserNotFoundException;
import com.user.exception.UserSaveException;
import com.user.exception.UserUpdateException;
import com.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveUser() throws UserSaveException {
		User user = new User(); // Create a user object
		when(userService.saveUser(any(User.class))).thenReturn(user);

		ResponseEntity<User> responseEntity = userController.saveUser(user);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(user, responseEntity.getBody());
	}

	@Test
	void testGetByUserId() throws UserNotFoundException {
		UserBean userBean = new UserBean(); // Create a user bean object
		when(userService.getByUserId(anyLong())).thenReturn(userBean);

		ResponseEntity<UserBean> responseEntity = userController.getByUserId(1L);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(userBean, responseEntity.getBody());
	}

	@Test
	void testGetAllUsers() throws NoUsersFoundException {
		List<User> userList = List.of(new User(), new User()); // Create a list of user objects
		when(userService.getAllUsers()).thenReturn(userList);

		ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(userList, responseEntity.getBody());
	}

	@Test
	void testDeleteUserById() throws UserDeleteException {
//        when(userService.deleteUserById(anyLong())).thenReturn(true);

		ResponseEntity<User> responseEntity = userController.deleteUserById(1L);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void testUpdateUser() throws UserUpdateException {
		User user = new User(); // Create a user object
		Optional<User> optionalUser = Optional.of(user);
		when(userService.updateUser(any(User.class))).thenReturn(optionalUser);

		ResponseEntity<Optional<User>> responseEntity = userController.updateUser(user);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(optionalUser, responseEntity.getBody());
	}

}
