package com.stock.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.dao.AdminDao;
import com.stock.model.Post;
import com.stock.model.User;
import com.stock.service.AdminServices;

/**
 * Class implementing the adminservice interface 
 * @author anitageorge and bhavya
 *
 */
@Service
@Transactional
public class AdminServicesImpl implements AdminServices {

	
	@Autowired
    private AdminDao adminDao;
	
	/**
	 * Forwards the User object to the DAO layer
	 */
	@Transactional
	public void addUser(User user) {
		adminDao.addUser(user);
		
	}
	
	/**
	 * Retrieves the list of Users from the DAO layer
	 */
	@Transactional
	public List<User> getAllUser() {
		return adminDao.getAllUser();
	}
	
	/**
	 * Forwards the User object to the DAO layer to delete
	 */
	@Transactional
	public void deleteUser(Integer userid) {
		adminDao.deleteUser(userid);
	}

	/**
	 * Retrieves the User based on id from the DAO layer
	 */
	public User getUser(int userid) {
		return adminDao.getUser(userid);
	}

	/**
	 * Forwards the User object to the DAO layer to update
	 */
	public User updateUser(User user) {
		return adminDao.updateUser(user);
	}
	
	
	
	
	
	
	@Transactional
	public void addPost(Post post) {
		adminDao.addPost(post);
		
	}
	@Transactional
	public List<Post> getAllPost() {
		return adminDao.getAllPost();
	}
	
	@Transactional
	public List<Post> getSpecificPosts(Integer userid){
		return adminDao.getSpecificPosts(userid);
	}
	@Transactional
	public void deletePost(Integer postid) {
		adminDao.deletePost(postid);
		
	}

	public Post getPost(int postid) {
		return adminDao.getPost(postid);
	}

	public Post updatePost(Post post) {
		return adminDao.updatePost(post);
	}
	
	
	public void setUsersDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
 

}
