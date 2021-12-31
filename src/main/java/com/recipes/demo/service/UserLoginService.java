package com.recipes.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.recipes.demo.request.UserDetailsRequestModel;
import com.recipes.demo.request.UserLoginRequest;
import com.recipes.demo.response.UserLoginResponse;
import com.recipes.demo.response.UserRest;

public interface UserLoginService extends UserDetailsService {
	
	UserLoginResponse login(UserLoginRequest req);

	UserRest createUser(UserDetailsRequestModel req);

	UserRest getUser(String id);
}
