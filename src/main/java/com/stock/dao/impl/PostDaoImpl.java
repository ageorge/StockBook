package com.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stock.dao.PostDao;
import com.stock.model.Post;
import com.stock.model.Reply;

/**
 * Class that implements the PostDao interface
 * @author anitageorge and bhavya
 *
 */
@Repository
@Transactional
public class PostDaoImpl implements PostDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Method to persist a new post into the post table
	 */
	@Override
	public void addPost(Post post) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(post);
		session.flush();
	}

	/**
	 * Method to retrieve a post from the database based on postid
	 */
	@Override
	public Post getPost(int postid) {
		Session session = sessionFactory.getCurrentSession();
		Post post = (Post) session.get(Post.class, postid);
		
		return post;
	}

	/**
	 * Method to get all posts from the database
	 */
	@Override
	public List<Post> getPosts() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Post>) session.createQuery("from Post").list();
	}

	/**
	 * Method to update a post in the database
	 */
	@Override
	public void modifyPost(Post post) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(post);
		session.flush();
	}

	/**
	 * Method to remove a post from the database
	 */
	@Override
	public void removePost(Post post) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(post);
		session.flush();
	}

	/**
	 * Method to persist a reply to the database
	 */
	@Override
	public void addReply(Reply reply) {
		Session session = sessionFactory.getCurrentSession();
		session.save(reply);
		session.flush();
	}

	/**
	 * Method to retrieve all replies made to a post
	 */
	@Override
	public List<Reply> getReply(int postid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Reply where postid = :postid");
		query.setParameter("postid", postid);
		
		return query.getResultList();
	}
}
