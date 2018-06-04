package com.stock.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity class that stores the post details
 * @author anitageorge and bhavya
 *
 */
@Entity
@Table(name="post", uniqueConstraints= {@UniqueConstraint(columnNames = {"postid"})})
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int postid;
	
	@Column
	private String postname;
	
	@Column
	private String message;
	
	@Column
	private String filename;
	
	@Column
	private byte[] contents;
	
	@Column
	private String post_status;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "stockid")
	private Stock stock;

	
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

	@OneToMany(mappedBy="post")
	List<Reply> replies;

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * @return the replies
	 */
	public List<Reply> getReplies() {
		return replies;
	}

	/**
	 * @param replies the replies to set
	 */
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	/**
	 * @return the contents
	 */
	public byte[] getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(byte[] contents) {
		this.contents = contents;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the postid
	 */
	public int getPostid() {
		return postid;
	}

	/**
	 * @param postid the postid to set
	 */
	public void setPostid(int postid) {
		this.postid = postid;
	}

	/**
	 * @return the postname
	 */
	public String getPostname() {
		return postname;
	}

	/**
	 * @param postname the postname to set
	 */
	public void setPostname(String postname) {
		this.postname = postname;
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
	
	/**
	 * @return the post_status
	 */
	public String getPost_status() {
		return post_status;
	}

	/**
	 * @param post_status the post_status to set
	 */
	public void setPost_status(String post_status) {
		this.post_status = post_status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Post [postid=" + postid + ", postname=" + postname + ", message=" + message + ", filename=" + filename
				+ ", post_status=" + post_status + ", userid=" + user + "]";
	}
	
}
