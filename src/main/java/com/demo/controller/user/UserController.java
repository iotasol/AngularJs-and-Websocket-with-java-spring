package com.demo.controller.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.user.User;
import com.demo.repository.user.UserRepository;
import com.demo.service.security.SecurityInfoProvider;
import com.demo.service.user.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger log = LogManager
			.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userFacade;

	@Autowired
	SecurityInfoProvider securityInfoProvider;

	/**
	 * Method to Get Login User
	 * 
	 * @return User
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public User getCurrentUser() {
		log.debug("Runing user getCurrentUser() in User Controller");
		return securityInfoProvider.getCurrentUser();
	}
}
