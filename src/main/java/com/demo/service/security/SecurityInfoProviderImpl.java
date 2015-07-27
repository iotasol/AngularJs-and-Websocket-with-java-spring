package com.demo.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.demo.model.user.User;
import com.demo.repository.user.UserRepository;

@Component
public class SecurityInfoProviderImpl implements SecurityInfoProvider {

	@Autowired
	UserRepository userRepository;

	User overridenCurrentUser;

	/**
	 * 
	 * Method to Get Current User
	 * 
	 * @return User
	 */
	public String getUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return null;
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	/**
	 * Method to get Authenticate User
	 * 
	 * @param user
	 */
	@Override
	public User getCurrentUser() {
		if (overridenCurrentUser != null) {
			return overridenCurrentUser;
		}
		User user = userRepository.findByUsername(getUsername());
		if (user == null)
			return user;
		return user;
	}

	// can use this to allow use of services that require a current user when
	// initializing test data or dummy data. Set to null after complete
	public void setCurrentUser(User user) {
		overridenCurrentUser = user;
	}

	private void authenticateWithAuthorities(User user,
			List<GrantedAuthority> authorities) {

		// update the authentication to reflect the new user
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	@Override
	public void AuthenticateUser(User user) {
		authenticateWithAuthorities(user, user.getRole()
				.getGrantedAuthorities());
	}

	/**
	 * Method to is CurrentUser
	 * 
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean isCurrentUser(User user) {
		User currentUser = getCurrentUser();

		return currentUser != null && user.getId().equals(currentUser.getId());
	}

}
