package com.pms.ust.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.ust.pms.service.MyUserDetailService;

@EnableWebSecurity

public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	
	@Autowired
	DataSource dataSource;
	//Enable JDBC authentication
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
		authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource);
	}
	
	
	//authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/Home").permitAll()
		.antMatchers("/view").permitAll()
		.antMatchers("/viewCart").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/clientHome").permitAll()
		.antMatchers("/cart").permitAll()
		.antMatchers("/searchCartProduct").hasAnyRole("USER","ADMIN")
		.antMatchers("/customerViewProducts").permitAll()
		.antMatchers("/index").hasAnyRole("USER","ADMIN")
		.antMatchers("/viewAllProducts").hasAnyRole("USER","ADMIN")
		.antMatchers("/addProduct").hasAnyRole("ADMIN")
		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		//super.configure(http);
		
		//cross site request frogery, we can disable it or its not mandatory to use csrf
		http.csrf().disable();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}
	

}
