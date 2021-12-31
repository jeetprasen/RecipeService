package com.recipes.demo.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.recipes.demo.service.UserLoginService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserLoginService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserLoginService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
		//http.cors().configurationSource(request -> corsConfiguration)
		http.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
		.permitAll()
		.antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**","/actuator/**")
        .permitAll()
        .antMatchers("/h2-console/**")
        .permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager())).sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.headers().frameOptions().sameOrigin();
	}


	@Bean
    CorsConfigurationSource corsConfigurationSource() 
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	/*
	 * @Bean CorsConfigurationSource corsConfigurationSource() {
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * new CorsConfiguration().applyPermitDefaultValues()); return source; }
	 */
	  
	 


	/*
	 * To use this add:
	 * http.cors().configurationSource(configurationSource()).add() in above configure
	 *
	 * private CorsConfigurationSource configurationSource() {
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource();
	 * 
	 * List<String> allowedOrigins = new ArrayList<String>();
	 * allowedOrigins.add("http://localhost:4200");
	 * 
	 * final CorsConfiguration config = new CorsConfiguration();
	 * config.setAllowedOriginPatterns(allowedOrigins);
	 * config.setAllowCredentials(true);
	 * config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
	 * config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
	 * "OPTIONS"));
	 * 
	 * source.registerCorsConfiguration("/**", config); return source; }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
}
