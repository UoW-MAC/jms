package com.jms.service.impl;

import org.easymock.EasyMock;
import org.junit.Before;

import com.jms.domain.entities.User;
import com.jms.domain.repositories.UserRepository;

import junit.framework.TestCase;

public class TestUserServiceImpl extends TestCase {

	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		userRepository = EasyMock.createMock(UserRepository.class);
		userRepository.register(EasyMock.<User> anyObject());
		userRepository.addUserAuthority(EasyMock.<User> anyObject());
	}

	// No exception happened in repository layer
	public void testRegister() {
		EasyMock.expectLastCall();
		EasyMock.replay(userRepository);

		UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);
		User user = new User();
		user.setUserName("test");
		user.setPassword("123456");
		int flag = 0;

		try {
			userServiceImpl.register(user);
			flag = 1;
		} catch (Exception e) {

		}
		assertEquals(1, flag);
	}

	// There is exception happened in repository layer
	public void testRegister1() {
		EasyMock.expectLastCall().andThrow(new RuntimeException());
		EasyMock.replay(userRepository);

		UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);
		User user = new User();
		user.setUserName("test");
		user.setPassword("123456");
		int flag = 0;

		try {
			userServiceImpl.register(user);
			flag = 1;
		} catch (Exception e) {

		}
		assertEquals(0, flag);
	}

	// User name exists
	public void testIsUserNameExisted() {
		User user = new User();
		user.setUserName("test");
		user.setPassword("123456");

		EasyMock.expect(userRepository.getUserByUserName("test")).andReturn(user);
		EasyMock.replay(userRepository);

		UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

		try {
			assertEquals(userServiceImpl.isUserNameExisted("test"), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// User name not exists
	public void testIsUserNameExisted1() {
		User user = null;

		EasyMock.expect(userRepository.getUserByUserName("test")).andReturn(user);
		EasyMock.replay(userRepository);

		UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

		try {
			assertEquals(userServiceImpl.isUserNameExisted("test"), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Email exists
	public void testIsEmailExisted() {
		User user = new User();
		user.setEmail("test@gmail.com");

		EasyMock.expect(userRepository.getUserByEmail("test@gmail.com")).andReturn(user);
		EasyMock.replay(userRepository);

		UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

		try {
			assertEquals(userServiceImpl.isEmailExisted("test@gmail.com"), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Email not exists
	public void testIsEmailExisted1() {
		User user = null;

		EasyMock.expect(userRepository.getUserByEmail("test@gmail.com")).andReturn(user);
		EasyMock.replay(userRepository);

		UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

		try {
			assertEquals(userServiceImpl.isEmailExisted("test@gmail.com"), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// User does not log in
	public void testIsLogged1() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		try {
			assertEquals(userServiceImpl.isLogged(), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
}
