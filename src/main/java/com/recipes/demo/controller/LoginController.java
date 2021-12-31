package com.recipes.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipes.demo.exception.UserServiceException;
import com.recipes.demo.request.UserDetailsRequestModel;
import com.recipes.demo.request.UserLoginRequest;
import com.recipes.demo.response.ErrorMessages;
import com.recipes.demo.response.UserLoginResponse;
import com.recipes.demo.response.UserRest;
import com.recipes.demo.service.UserLoginService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/app")
public class LoginController {

	@Autowired
	UserLoginService userLoginService;

	@PostMapping("/login")
	public ResponseEntity<UserLoginResponse> createNewData(@RequestBody UserLoginRequest req) {

		UserLoginResponse resp = userLoginService.login(req);

		if (resp.getEmail() != null && resp.getEmail() != "") {
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(path = "/create")
	public UserRest createUser(@RequestBody UserDetailsRequestModel req) throws Exception { 
		UserRest resp = new UserRest();
		
		if(null == req.getFirstName() || req.getFirstName().isEmpty()) 
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
		
		resp = userLoginService.createUser(req);
		return resp;
	}
	
	@PostMapping("/test")
	public ResponseEntity<String> test(@RequestBody String req) {

		return new ResponseEntity<>(req, HttpStatus.OK);

	}
	
	@GetMapping
	public UserRest getUser(@PathVariable String id) {
		return userLoginService.getUser(id);
		
	}
}
