package com.testSpring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("noticesDao")
@Transactional
public class NoticesDao {

	public NoticesDao() {
		System.out.println("creating Notices DAO");
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// get all notices in a list.
	@SuppressWarnings("unchecked")
	public List<Notice> getNotices() {
		Criteria crit = getSession().createCriteria(Notice.class);

		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		return crit.list();

	}

	// get all notices of a perticular user
	@SuppressWarnings("unchecked")
	public List<Notice> getNotices(String username) {
		Criteria crit = getSession().createCriteria(Notice.class);

		crit.createAlias("user", "u");
		
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", username));
				
		return crit.list();
	}

	// get a specific notice with id.
	public Notice getNotice(int identificationNumber) {
		Criteria crit = getSession().createCriteria(Notice.class);

		crit.createAlias("user", "u");
		
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(identificationNumber));
		return (Notice) crit.uniqueResult();
	}

	// delete a specific notice with id.
	public boolean deleteNotice(int identification) {
		Query query = getSession().createQuery("delete from Notice where id=:id");
		query.setLong("id", identification);
		return query.executeUpdate() == 1;

	}

	// save or update a Notice/
	public void saveOrUpdateNotice(Notice notice) {
		getSession().saveOrUpdate(notice);
	}
}







