package com.recipes.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipes.demo.SpringApplicationContext;
import com.recipes.demo.request.UserLoginRequest;
import com.recipes.demo.response.UserLoginResponse;
import com.recipes.demo.response.UserRest;
import com.recipes.demo.service.UserLoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException ie) {
			throw new RuntimeException();
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String userName = ((User)authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
							.setSubject(userName)
							.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_DATE))
							.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
							.compact();
		UserLoginService userService = (UserLoginService) SpringApplicationContext.getBean("userLoginServiceImpl");
		UserRest user = userService.getUser(userName);
		
		// Set Header
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("userId", user.getUserId());

		// Set Response Object
		UserLoginResponse userResp = new UserLoginResponse();
		userResp.setEmail(user.getEmail());
		userResp.setUserId(user.getUserId());
		userResp.setToken(SecurityConstants.TOKEN_PREFIX + token);
		userResp.setExpiresIn(SecurityConstants.EXPIRATION_DATE);
		
		// Create and write with mapper
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userResp);
		response.getWriter().write(jsonString);
		response.setContentType("applicaton/json");
	}

}
