package com.stock.service;

import java.util.List;

import com.stock.model.User;


/**
 * Interface to process user interaction with the database
 * @author anitageorge and bhavya
 *
 */
public interface UserService {
	
	public void addUser(User user);
	public User getUser(int userid);
	public User validateUser(String username, String password);
	public List<User> getUsers();
	public void modifyUser(User user);
	public void removeUser(User user);
	
}
