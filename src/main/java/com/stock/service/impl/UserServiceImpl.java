package com.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.UserDao;
import com.stock.model.User;
import com.stock.service.UserService;

/**
 * Class implementing the UserService interface 
 * @author anitageorge and bhavya
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userdao;

	/**
	 * Forwards the user object to the DAO layer
	 */
	@Override
	public void addUser(User user) {
		userdao.addUser(user);
	}

	/**
	 * Retrieves the users based on id from the DAO layer
	 */
	@Override
	public User getUser(int userid) {
		return userdao.getUser(userid);
	}

	/**
	 * Retrieves all users from the DAO layer
	 */
	@Override
	public List<User> getUsers() {
		return userdao.getUsers();
	}

	/**
	 * Forwards the User object to the DAO layer to update
	 */
	@Override
	public void modifyUser(User user) {
		userdao.modifyUser(user);
	}

	/**
	 * Forwards the User object to the DAO layer to remove
	 */
	@Override
	public void removeUser(User user) {
		userdao.removeUser(user);
	}

	/**
	 * Retrieves the user details from the DAO layer after validation
	 */
	@Override
	public User validateUser(String username, String password) {
		return userdao.validateUser(username, password);
	}

	
	
}
