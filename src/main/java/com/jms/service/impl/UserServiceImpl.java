package com.jms.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.domain.entities.User;
import com.jms.domain.repositories.UserRepository;
import com.jms.infrastructure.util.PasswordGenerator;
import com.jms.service.IUserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

	public void register(User user) throws Exception {
		String encryptedPassword = PasswordGenerator.encryptPassword(user.getPassword(), user.getUserName());
		user.setPassword(encryptedPassword);
		
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		user.setCreateTime(createTime);
		user.setUpdateTime(createTime);
		
		userRepository.register(user);
		userRepository.addUserAuthority(user);
	}

	public boolean isUserNameExisted(String userName) throws Exception {
		User user = userRepository.getUserByUserName(userName);
		Boolean isUserNameExisted = false;
		
		if (user != null) {
			isUserNameExisted = true;
		}
		
		return isUserNameExisted;
	}

	public boolean isEmailExisted(String email) throws Exception {
		User user = userRepository.getUserByEmail(email);
		Boolean isEmailExisted = false;
		
		if (user != null) {
			isEmailExisted = true;
		}
		
		return isEmailExisted;
		
	}

	public boolean isLogged() throws Exception {
		Boolean result = false;
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userDetails.getUsername();
		
		if (userName != null) {
			result = true;
		}
		
		return result;
	}
}
