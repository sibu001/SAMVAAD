package com.divergent.meet.divergentmeet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divergent.meet.divergentmeet.model.User;
import com.divergent.meet.divergentmeet.payload.JwtAuthenticationResponse;
import com.divergent.meet.divergentmeet.payload.LoginRequest;
import com.divergent.meet.divergentmeet.payload.RefreshTokenRequest;
import com.divergent.meet.divergentmeet.security.JwtTokenProvider;
import com.divergent.meet.divergentmeet.security.UserPrincipal;
import com.divergent.meet.divergentmeet.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;
	


	/**
	 * login API (generate access token)
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		long id = userPrincipal.getId();
		String jwt = tokenProvider.generateToken(userPrincipal);
		String refreshToken = tokenProvider.generateRefreshToken(userPrincipal);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, refreshToken, id, "Success"));
	}

	/**
	 * To generate access token via refresh token
	 * 
	 * @param refreshTokenRequest
	 * @return
	 */
	@PostMapping("/refreshToken")
	public ResponseEntity<JwtAuthenticationResponse> refreshAccessToken(
			@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {

		String email = tokenProvider.getUserFromJWT(refreshTokenRequest.getRefreshToken());
		User user = userService.findById(Long.parseLong(email));
		
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		String jwt = tokenProvider.generateToken(userPrincipal);
		String refreshToken = tokenProvider.generateRefreshToken(userPrincipal);
		long id = userPrincipal.getId();
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, refreshToken, id, "Success"));
	}
	
}

