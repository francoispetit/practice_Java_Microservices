package com.formacionbdi.springboot.app.users.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.formacionbdi.springboot.app.users.models.entity.User;

@RepositoryRestResource(path="users")
public interface UserDao extends PagingAndSortingRepository<User, Long>{

	@RestResource(path="find-username")
	public User findByUsername(@Param("name") String username);
	
	@Query(value="select u from User u where u.username=?1")
	public User getFromUsername(String username);
}
