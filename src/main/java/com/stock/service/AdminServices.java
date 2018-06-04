package com.stock.service;

import java.util.List;

import com.stock.model.Post;
import com.stock.model.User;

/**
 * Interface to process admin interaction with the database
 * @author anitageorge and bhavya
 *
 */
public interface AdminServices {
	
	public void addUser(User user);
	 
    public List<User> getAllUser();
 
    public void deleteUser(Integer userid);
 
    public User getUser(int userid);
 
    public User updateUser(User user);
    
    
    
    public void addPost(Post post);
	 
    public List<Post> getAllPost();
    
    public List<Post> getSpecificPosts(Integer userid);
 
    public void deletePost(Integer postid);
 
    public Post getPost(int postid);
 
    public Post updatePost(Post post);

}
