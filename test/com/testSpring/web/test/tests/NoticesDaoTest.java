package com.testSpring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.testSpring.web.dao.Notice;
import com.testSpring.web.dao.NoticesDao;
import com.testSpring.web.dao.User;
import com.testSpring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/testSpring/web/config/dao-context.xml",
		"classpath:com/testSpring/web/config/security-context.xml",
		"classpath:com/testSpring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class NoticesDaoTest {

	@Autowired
	private NoticesDao noticesDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("user1", "User One", "stdstd123", "tareq@tareq.com", true, "ROLE_USER");
	private User user2 = new User("user2", "User Two", "stdstd123", "tareq@tareq.com", true, "ROLE_ADMIN");
	private User user3 = new User("user3", "User Three", "stdstd123", "tareq@tareq.com", true, "ROLE_USER");
	private User user4 = new User("user4", "User Four", "stdstd123", "tareq@tareq.com", false, "ROLE_USER");

	private Notice notice1 = new Notice(user1, "This is a test notice for user 1");
	private Notice notice2 = new Notice(user1, "This is a test notice for user 1 again");
	private Notice notice3 = new Notice(user2, "This is a test notice for user 2");
	private Notice notice4 = new Notice(user3, "This is a test notice for user 3");
	private Notice notice5 = new Notice(user3, "This is a test notice for user 3 again");
	private Notice notice6 = new Notice(user3, "This is a test notice for user 3 once again");
	private Notice notice7 = new Notice(user4, "This is a test notice for user 4");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from notices");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		noticesDao.saveOrUpdateNotice(notice1);

		List<Notice> notices1 = noticesDao.getNotices();

		assertEquals("Should be 1 notice", 1, notices1.size());
		assertEquals("Inserted and retrieved notice should be same", notice1, notices1.get(0));

		noticesDao.saveOrUpdateNotice(notice2);
		noticesDao.saveOrUpdateNotice(notice3);
		noticesDao.saveOrUpdateNotice(notice4);
		noticesDao.saveOrUpdateNotice(notice5);
		noticesDao.saveOrUpdateNotice(notice6);
		noticesDao.saveOrUpdateNotice(notice7);

		List<Notice> notices2 = noticesDao.getNotices();

		assertEquals("Should be 6 notices for enabled users", 6, notices2.size());
	}
	
	@Test
	public void testGetUsername(){
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		noticesDao.saveOrUpdateNotice(notice1);
		noticesDao.saveOrUpdateNotice(notice2);
		noticesDao.saveOrUpdateNotice(notice3);
		noticesDao.saveOrUpdateNotice(notice4);
		noticesDao.saveOrUpdateNotice(notice5);
		noticesDao.saveOrUpdateNotice(notice6);
		noticesDao.saveOrUpdateNotice(notice7);
		
		List<Notice> notices1 = noticesDao.getNotices(user3.getUsername());
		assertEquals("should be three notices for this user", 3, notices1.size());
		
		List<Notice> notices2 = noticesDao.getNotices("xyz");
		assertEquals("should be zero notices for this user", 0, notices2.size());
		
		List<Notice> notices3 = noticesDao.getNotices(user2.getUsername());
		assertEquals("should be one notices for this user 2", 1, notices3.size());
	}
	
	@Test
	public void testUpdate(){
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);
		noticesDao.saveOrUpdateNotice(notice1);
		noticesDao.saveOrUpdateNotice(notice2);
		noticesDao.saveOrUpdateNotice(notice3);
		noticesDao.saveOrUpdateNotice(notice4);
		noticesDao.saveOrUpdateNotice(notice5);
		noticesDao.saveOrUpdateNotice(notice6);
		noticesDao.saveOrUpdateNotice(notice7);
		
		notice3.setText("notice 3 has been updated with valid information.");
		noticesDao.saveOrUpdateNotice(notice3);
		
		Notice retrieved = noticesDao.getNotice(notice3.getId());
		
		assertEquals("Retrieved notice should be updated", notice3, retrieved);
		System.out.println(retrieved.getText());

	}
	
	@Test
	public void testDelete(){
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);
		noticesDao.saveOrUpdateNotice(notice1);
		noticesDao.saveOrUpdateNotice(notice2);
		noticesDao.saveOrUpdateNotice(notice3);
		noticesDao.saveOrUpdateNotice(notice4);
		noticesDao.saveOrUpdateNotice(notice5);
		noticesDao.saveOrUpdateNotice(notice6);
		noticesDao.saveOrUpdateNotice(notice7);
		
		notice3.setText("notice 3 has been updated with valid information.");
		noticesDao.saveOrUpdateNotice(notice3);
		
		Notice retrieved1 = noticesDao.getNotice(notice2.getId());
		assertNotNull("notice with id " + notice2.getId()+" should not be null.", retrieved1);
		
		noticesDao.deleteNotice(notice2.getId());
		
		Notice retrieved2 = noticesDao.getNotice(notice2.getId());
		assertNull("notice with id " + notice2.getId()+" should be null.", retrieved2);
	}

	
	
	@Test
	public void testNotices() {
		User user = new User("tareq", "Tareq Sefati", "stdstd123", "tareq.sefati@gmail.com", true, "ROLE_USER");

		usersDao.createUser(user);

		Notice notice = new Notice(user, "This is a test Notice. This is a new Notice.");

		noticesDao.saveOrUpdateNotice(notice);

		List<Notice> notices = noticesDao.getNotices();

		assertEquals("Should be one notice", 1, notices.size());

		assertEquals("Retrieved notice should be match the created", notice, notices.get(0));

		notice = notices.get(0);
		notice.setText("Notice is updated Notice is updated Notice is updated");

		noticesDao.saveOrUpdateNotice(notice);

		Notice updated = noticesDao.getNotice(notice.getId());

		assertEquals("updated and retrieved notice should match", notice, updated);

		Notice notice2 = new Notice(user, "This is anothrer test notices. another test notice.");
		noticesDao.saveOrUpdateNotice(notice2);
		List<Notice> userNotices = noticesDao.getNotices(user.getUsername());
		assertEquals("Should be 2 notices for this user.", 2, userNotices.size());

		noticesDao.deleteNotice(notice.getId());

		List<Notice> empty = noticesDao.getNotices();
		assertEquals("Notice list should be one", 1, empty.size());
	}
}
