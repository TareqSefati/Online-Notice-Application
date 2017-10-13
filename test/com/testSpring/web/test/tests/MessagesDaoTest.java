package com.testSpring.web.test.tests;

import static org.junit.Assert.assertEquals;

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

import com.testSpring.web.dao.Message;
import com.testSpring.web.dao.MessagesDao;
import com.testSpring.web.dao.User;
import com.testSpring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/testSpring/web/config/dao-context.xml",
		"classpath:com/testSpring/web/config/security-context.xml",
		"classpath:com/testSpring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagesDaoTest {

	@Autowired
	private MessagesDao messagesDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("user1", "User One", "stdstd123", "tareq@tareq.com", true, "ROLE_USER");
	private User user2 = new User("user2", "User Two", "stdstd123", "tareq@tareq.com", true, "ROLE_ADMIN");
	private User user3 = new User("user3", "User Three", "stdstd123", "tareq@tareq.com", true, "ROLE_USER");
	private User user4 = new User("user4", "User Four", "stdstd123", "tareq@tareq.com", false, "ROLE_USER");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from notices");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testSave() {
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);
		
		Message message = new Message("Test Subject", "Test Content", "Hayat", "tatour@tk.com",
				user1.getUsername());
		messagesDao.saveOrUpdateMessage(message);
	}
}






