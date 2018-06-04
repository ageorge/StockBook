package com.stock.model;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Class that is encapsulates all necessary data to display the newsfeed 
 * @author anitageorge and bhavya
 *
 */
public class NewsFeed {
	private Post post;
	private String image;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage() {
		if(post.getContents() != null) {
			byte[] encodeBase64 = Base64.encodeBase64(post.getContents());
            try {
				image = new String(encodeBase64, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("Full Image = " + image);
		} else {
			System.out.println("No Image = " + image);
		}
	}
}
