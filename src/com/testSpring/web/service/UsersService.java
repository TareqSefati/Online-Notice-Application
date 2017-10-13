package com.testSpring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.testSpring.web.dao.Message;
import com.testSpring.web.dao.MessagesDao;
import com.testSpring.web.dao.User;
import com.testSpring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private MessagesDao messagesDao;
	
	public void createUser(User user) {
		usersDao.createUser(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	public void sendMessage(Message message){
		System.out.println(message);
		messagesDao.saveOrUpdateMessage(message);
	}
}
