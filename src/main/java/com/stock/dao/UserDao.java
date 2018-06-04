package com.stock.dao;

import java.util.List;

import com.stock.model.User;


/**
 * Interface for interacting with the user table in database
 * @author anitageorge and bhavya
 *
 */
public interface UserDao {
	
	public void addUser(User user);
	public User getUser(int userid);
	public List<User> getUsers();
	public void modifyUser(User user);
	public void removeUser(User user);
	public User validateUser(String username, String password);
}
