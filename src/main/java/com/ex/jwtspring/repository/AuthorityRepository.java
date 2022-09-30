package com.ex.jwtspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ex.jwtspring.entity.Authority;
import com.ex.jwtspring.entity.AuthorityId;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {

	@Query(value="SELECT role from Authority a WHERE a.username = :username",nativeQuery = true)
	List<String> getAuthoritiesByUserName(String username);

}
