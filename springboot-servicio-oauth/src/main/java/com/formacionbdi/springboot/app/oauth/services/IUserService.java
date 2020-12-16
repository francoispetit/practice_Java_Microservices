package com.formacionbdi.springboot.app.oauth.services;

import com.formacionbdi.springboot.app.commons.users.models.entity.User;

public interface IUserService {
	
	public User findByUsername(String username);
	public User update(User user, Long id);


}
