package com.stock.dao;

import java.util.List;

import com.stock.model.Post;
import com.stock.model.Reply;


/**
 * Interface for interacting with the post table in database
 * @author anitageorge and bhavya
 *
 */
public interface PostDao {
	public void addPost(Post post);
	public void addReply(Reply reply);
	public List<Reply> getReply(int postid);
	public Post getPost(int postid);
	public List<Post> getPosts();
	public void modifyPost(Post post);
	public void removePost(Post post);
}
