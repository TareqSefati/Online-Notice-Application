package com.testSpring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("messagesDao")
@Transactional
public class MessagesDao {

	private NamedParameterJdbcTemplate jdbc;
	
	public MessagesDao() {
		System.out.println("creating Messages DAO");
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// get all messages in a list.
	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		Criteria crit = getSession().createCriteria(Message.class);
		return crit.list();

	}

	// get all messages of a perticular user
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {
		Criteria crit = getSession().createCriteria(Message.class);
		crit.add(Restrictions.eq("username", username));	
		return crit.list();
	}

	// get a specific message with id.
	public Message getMessage(int identificationNumber) {
		Criteria crit = getSession().createCriteria(Message.class);
		crit.add(Restrictions.idEq(identificationNumber));
		return (Message) crit.uniqueResult();
	}

	// delete a specific message with id.
	public boolean deleteMessage(int identification) {
		Query query = getSession().createQuery("delete from Message where id=:id");
		query.setLong("id", identification);
		return query.executeUpdate() == 1;

	}

	// save or update a Message
	public void saveOrUpdateMessage(Message message) {
		getSession().saveOrUpdate(message);
	}
}







