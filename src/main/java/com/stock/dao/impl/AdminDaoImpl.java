package com.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stock.dao.AdminDao;
import com.stock.model.Post;
import com.stock.model.User;

/**
 * Class that implements the AdminDao interface
 * @author anitageorge and bhavya
 *
 */
@Repository
@Transactional
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	/**
	 * Method to persist the user details on the user table
	 */
	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	
	/**
	 * Method to retrieve all user details from the table
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	/**
	 * Method to delete the user from the table
	 */
	public void deleteUser(Integer userid) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class, userid);
        if (null != user) {
            this.sessionFactory.getCurrentSession().delete(user);
        }
	}

	/**
	 * Method to update the user details in the table
	 */
	public User updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
        return user;
	}
	
	/**
	 * Method to retrieve user details based on userid
	 */
	public User getUser(int userid) {
		 return (User) sessionFactory.getCurrentSession().get(User.class, userid);
	}
	
	/**
	 * Method to persist the post details in the post table
	 */
	public void addPost(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
		
	}
	@SuppressWarnings("unchecked")
	public List<Post> getAllPost() {
		return sessionFactory.getCurrentSession().createQuery("from Post").list();
	}
	
	/**
	 * Method to retrieve all posts made by a user
	 */
	public List<Post> getSpecificPosts(Integer userid){
		Query query = sessionFactory.getCurrentSession().createQuery("from Post where userid = :userid ");
		query.setParameter("userid", userid);
		return query.getResultList();
	}

	/**
	 * Method to delete the post from the database
	 */
	public void deletePost(Integer postid) {
		Post post = (Post) sessionFactory.getCurrentSession().load(Post.class, postid);
        if (null != post) {
            this.sessionFactory.getCurrentSession().delete(post);
        }
	}
	
	/**
	 * Method to update the post details on the database
	 */
	public Post updatePost(Post post) {
		sessionFactory.getCurrentSession().update(post);
        return post;
	}
	
	/**
	 * Method to get the post details based on post id
	 */
	public Post getPost(int postid) {
		 return (Post) sessionFactory.getCurrentSession().get(Post.class, postid);
	}

}
