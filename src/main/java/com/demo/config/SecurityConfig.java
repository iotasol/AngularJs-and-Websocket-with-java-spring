package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.service.security.AjaxLogoutSuccessHandler;
import com.demo.service.security.LoginFailureHandler;
import com.demo.service.security.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

	@Autowired
	AjaxAwareAuthenticationEntryPoint ajaxAwareAuthenticationEntryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(
				passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginProcessingUrl("/app/authentication")
				.usernameParameter("j_username")
				.passwordParameter("j_password")
				.successHandler(new LoginSuccessHandler())
				.failureHandler(new LoginFailureHandler()).permitAll().and()
				.logout().logoutUrl("/app/logout")
				.logoutSuccessHandler(ajaxLogoutSuccessHandler)
				.deleteCookies("JSESSIONID").permitAll().and().csrf().disable()
				.authorizeRequests().antMatchers("/user").authenticated()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/websocket").permitAll().antMatchers("/api/**")
				.authenticated().and().exceptionHandling()
				.authenticationEntryPoint(ajaxAwareAuthenticationEntryPoint);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
