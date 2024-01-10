package com.springboot.mydairy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mydairy.entity.User;
import com.springboot.mydairy.repo.UserRepo;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserRepo userrepo;	
	
	@Override
	public User saveUser(User user) {
		return userrepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userrepo.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userrepo.delete(user);
	}

	@Override
	public User findById(long id) {
		return userrepo.findById(id).get();
	}

	@Override
	public List<User> findAll() {
		return userrepo.findAll();
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userrepo.findByUsername(username);
	}

}
