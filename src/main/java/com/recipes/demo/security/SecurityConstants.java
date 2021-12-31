package com.recipes.demo.security;

import com.recipes.demo.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_DATE = 3600000;	//864000000; 	//10 days - in millisecond
	public static final long EXPIRATION_TIME = 60000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/app/create";
    public static final String H2_CONSOLE = "/h2-console/**";
	//public static final String TOKEN_SECRET = "jdfg6t78ut";
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}
