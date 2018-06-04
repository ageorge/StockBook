package com.stock.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class that stores the reply details
 * @author anitageorge and bhavya
 *
 */
@Entity
@Table(name="reply")
public class Reply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int replyid;
	
	@Column
	private String message;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="postid")
	private Post post;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid")
	private User user;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the post
	 */
	public Post getPost() {
		return post;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(Post post) {
		this.post = post;
	}

	/**
	 * @return the replyid
	 */
	public int getReplyid() {
		return replyid;
	}

	/**
	 * @param replyid the replyid to set
	 */
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

//	/**
//	 * @return the postid
//	 */
//	public int getPostid() {
//		return postid;
//	}
//
//	/**
//	 * @param postid the postid to set
//	 */
//	public void setPostid(int postid) {
//		this.postid = postid;
//	}

}
