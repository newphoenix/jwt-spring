package com.ex.jwtspring.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ex.jwtspring.config.JwtTokenUtil;
import com.ex.jwtspring.dto.CredentialDto;
import com.ex.jwtspring.dto.JwtResponse;
import com.ex.jwtspring.dto.UserDto;
import com.ex.jwtspring.repository.AuthorityRepository;
import com.ex.jwtspring.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private AuthorityRepository authorityRepository;
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userRepository.findByUsername(username)
				.map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(),
						user.getPassword(), //
						authorityRepository.getAuthoritiesByUserName(user.getUsername()).stream()
								.map(a -> new SimpleGrantedAuthority(a)).toList()))
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return userDetails;
	}

	public JwtResponse getToken(CredentialDto credentialDto) {

		final UserDetails userDetails = loadUserByUsername(credentialDto.getUsername());

		Set<String> authorities = userDetails.getAuthorities().stream().map(e -> e.getAuthority())
				.collect(Collectors.toSet());

		final String token = jwtTokenUtil.generateToken(userDetails, authorities);
		return new JwtResponse(token);
	}

	public List<UserDto> getUsers() {
		return userRepository.findAll().stream().map(user -> UserDto.builder().email(user.getEmail())
				.enabled(user.isEnabled()).username(user.getUsername()).build()).toList();
	}

}