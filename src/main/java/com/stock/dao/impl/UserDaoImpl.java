package com.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stock.dao.UserDao;
import com.stock.model.User;

/**
 * 
 * Class that implements the UserDao interface
 * @author anitageorge and bhavya
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Method to add the User details into the database
	 */
	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		session.flush();
	}

	/**
	 * Method to retrieve the user details from the database based on userid
	 */
	@Override
	public User getUser(int userid) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, userid);
		
		return user;
	}
	
	/**
	 * Method to get all users from database
	 */
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		return (List<User>) session.createQuery("from User").list();
	}

	/**
	 * Method to modify the user details
	 */
	@Override
	public void modifyUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		session.flush();
	}

	/**
	 * Method to remove the user from the database
	 */
	@Override
	public void removeUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
		session.flush();
	}

	/**
	 * Method to validate the user 
	 */
	@Override
	public User validateUser(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User as u where u.user_name = :username");
		query.setParameter("username", username);
		List<User> users = query.getResultList();
		User user = null;
		for(User u : users) {
			if(u.getPassword().equals(password)) {
				user = u;
				break;
			}
		}
		
		return user;
	}
	
}
