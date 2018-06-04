package com.stock.service;

import java.util.List;
import java.util.Set;

import com.stock.model.NewsFeed;
import com.stock.model.Post;
import com.stock.model.Reply;


/**
 * Interface to process post interaction with the database
 * @author anitageorge and bhavya
 *
 */
public interface PostService {
	public void addPost(Post post);
	public void addReply(Reply reply);
	public Post getPost(int postid);
	public List<NewsFeed> getPosts();
	public List<Post> getAllPost();
	public void modifyPost(Post post);
	public void removePost(Post post);
}
