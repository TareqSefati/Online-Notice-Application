package com.testSpring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.testSpring.web.dao.Notice;
import com.testSpring.web.dao.NoticesDao;

@Service("noticesService")
public class NoticesService {
	private NoticesDao noticesDAO;

	@Autowired
	public void setNoticesDAO(NoticesDao noticesDAO) {
		this.noticesDAO = noticesDAO;
	}
	
	public List<Notice> getCurrentNotices(){
		return noticesDAO.getNotices();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void createNotice(Notice notice) {
		noticesDAO.saveOrUpdateNotice(notice);
	}

	public boolean hasNotice(String name) {
		if(name == null)
			return false;
		
		List<Notice> notices = noticesDAO.getNotices(name);
		if(notices.size() == 0)
			return false;
		
		return true;
	}

	public Notice getNotice(String username) {
		if(username == null)
			return null;
		
		List<Notice> notices = noticesDAO.getNotices(username);
		if(notices.size() == 0)
			return null;
		
		return notices.get(0);
	}

	public void saveOrUpdate(Notice notice) {
		noticesDAO.saveOrUpdateNotice(notice);
	}

	public void delete(int id) {
		noticesDAO.deleteNotice(id);
	}
}
