package com.testSpring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//create a User
	@Transactional
	public void createUser(User user){
		//using hibernate
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		getSession().save(user);
	}

	public boolean exists(String username) {
		Criteria crit = getSession().createCriteria(User.class);
		
		//crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.idEq(username));
		
		User user = (User) crit.uniqueResult();
		
		return user != null;
	}

	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return getSession().createQuery("from User").list();
	}

	public User getUser(String username) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		return (User) crit.uniqueResult();
	}
}














