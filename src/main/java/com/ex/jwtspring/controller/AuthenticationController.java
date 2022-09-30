package com.ex.jwtspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ex.jwtspring.dto.CredentialDto;
import com.ex.jwtspring.dto.JwtResponse;
import com.ex.jwtspring.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {

	private UserService userService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> authenticate(@RequestBody CredentialDto credentialDto) {
			return ResponseEntity.ok(userService.getToken(credentialDto));
				
	}
}
