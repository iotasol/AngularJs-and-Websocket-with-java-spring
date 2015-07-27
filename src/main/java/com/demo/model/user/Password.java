package com.demo.model.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

	/**
	 * Method to get Encrypted Password
	 * 
	 * @param password
	 * @return String
	 */
	public static String getEncryptedPassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(password.toLowerCase());
	}

	/**
	 * Method to match Password
	 * 
	 * @param password
	 * @param encodedPassword
	 * @return boolean
	 */
	public static boolean matchPassword(String password, String encodedPassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(password, encodedPassword);
	}

}
