package com.demo.service.security;

import com.demo.model.user.User;

public interface SecurityInfoProvider {

	/**
	 * 
	 * Method to Get Current User
	 * 
	 * @return User
	 */
	User getCurrentUser();

	/**
	 * Method to get Authenticate User
	 * 
	 * @param user
	 */
	void AuthenticateUser(User user);

	/**
	 * Method to is CurrentUser
	 * 
	 * @param user
	 * @return boolean
	 */
	boolean isCurrentUser(User user);
}
