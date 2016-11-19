package com.clement.eventtracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.clement.eventtracker.PropertyManager;

/**
 * This is where the security is configured.
 * 
 * @author Clement_Soullard
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 
	 */
	@Autowired
	PropertyManager propertyManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/manager.html")
					.authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		if (propertyManager.getProductionMode()) {
			auth.ldapAuthentication().userDnPatterns("uid={0},ou=Persons").groupSearchBase("ou=groups").contextSource()
					.url("ldap://10.161.86.8:10399/dc=infosys,dc=com");
		} else {
			 auth
	            .inMemoryAuthentication()
	                .withUser("clement_soullard").password("test").roles("USER");
		}
	}
}
