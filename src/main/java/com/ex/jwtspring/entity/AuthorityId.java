package com.ex.jwtspring.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityId  implements java.io.Serializable {

	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "role", nullable = false)
	private String role;	
}
