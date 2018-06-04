package com.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dao.PostDao;
import com.stock.model.NewsFeed;
import com.stock.model.Post;
import com.stock.model.Reply;
import com.stock.service.PostService;
import com.stock.util.Constants;

/**
 * Class implementing the PostService interface 
 * @author anitageorge and bhavya
 *
 */
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDao postDao;

	/**
	 * Forwards the Post object to the DAO layer
	 */
	@Override
	public void addPost(Post post) {
		postDao.addPost(post);
	}

	/**
	 * Retrieves the Post based on id from the DAO layer
	 */
	@Override
	public Post getPost(int postid) {
		return postDao.getPost(postid);
	}

	/**
	 * Retrieves all Posts from the DAO layer and forms the NewsFeed object
	 */
	@Override
	public List<NewsFeed> getPosts() {
		List<NewsFeed> newsfeeds = new ArrayList<>();
		
		List<Post> posts = postDao.getPosts();
		for(Post post : posts) {
			if(post.getPost_status().equals(Constants.POST_APPROVED)) {
				post.setReplies(postDao.getReply(post.getPostid()));
				NewsFeed newsfeed = new NewsFeed();
				newsfeed.setPost(post);
				newsfeed.setImage();
				newsfeeds.add(newsfeed);
			}
		}
		
		return newsfeeds;
	}
	
	/**
	 * Retrieves all Posts from the DAO layer
	 */
	@Override
	public List<Post> getAllPost() {
		List<Post> posts = postDao.getPosts();
		return posts;
	}

	/**
	 * Forwards the Post object to the DAO layer to update
	 */
	@Override
	public void modifyPost(Post post) {
		postDao.modifyPost(post);
	}

	/**
	 * Forwards the Post object to the DAO layer to remove
	 */
	@Override
	public void removePost(Post post) {
		postDao.removePost(post);
	}

	/**
	 * Forwards the Reply object to the DAO layer to save
	 */
	@Override
	public void addReply(Reply reply) {
		postDao.addReply(reply);
	}

}
