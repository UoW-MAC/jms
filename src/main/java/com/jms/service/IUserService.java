package com.jms.service;

import com.jms.domain.entities.User;

/**
 * 
 * @author williamzhang
 *
 */
public interface IUserService {

	public void register(User user) throws Exception;

	public boolean isUserNameExisted(String userName) throws Exception;

	public boolean isEmailExisted(String email) throws Exception;

	public boolean isLogged() throws Exception;
}
