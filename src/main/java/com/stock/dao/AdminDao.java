package com.stock.dao;

import java.util.List;

import com.stock.model.Post;
import com.stock.model.User;

/**
 * Interface for admin user interaction with the database
 * @author anitageorge and bhavya
 *
 */
public interface AdminDao {

	public void addUser(User user);
	 
    public List<User> getAllUser();
 
    public void deleteUser(Integer userid);
 
    public User updateUser(User user);
 
    public User getUser(int userid);
    
    public void addPost(Post post);
	 
    public List<Post> getAllPost();
    
    public List<Post> getSpecificPosts(Integer userid);
 
    public void deletePost(Integer postid);
 
    public Post updatePost(Post post);
 
    public Post getPost(int postid);
}
