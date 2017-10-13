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

import com.testSpring.web.dao.User;
import com.testSpring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/testSpring/web/config/dao-context.xml",
		"classpath:com/testSpring/web/config/security-context.xml",
		"classpath:com/testSpring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

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
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrive() {
		usersDao.createUser(user1);

		List<User> users1 = usersDao.getAllUsers();

		assertEquals("One user should creaed and retrived.", 1, users1.size());

		assertEquals("Created user should match retrieved user.", user1, users1.get(0));

		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		List<User> users2 = usersDao.getAllUsers();

		assertEquals("Four user should creaed and retrived.", 4, users2.size());
	}

	
	@Test
	public void testExists() {
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		
		assertTrue("User should exist.", usersDao.exists(user2.getUsername()));
		assertFalse("User should not exists", usersDao.exists("xyz"));
	}
	
	// TODO - Reimplement this
	@Test
	public void testCreateUser() {
		User user = new User("tareq", "Tareq Sefati", "stdstd123", "tareq@tareq.com", true, "ROLE_USER");

		// assertEquals("Dummy Test", 1, 1);

		usersDao.createUser(user);

		List<User> users = usersDao.getAllUsers();

		assertEquals("Number of user should be 1.", 1, users.size());

		assertTrue("User should exist.", usersDao.exists(user.getUsername()));
		assertFalse("User should not exists", usersDao.exists("xyz"));

		assertEquals("Created user should be equal to the retrieved user", user, users.get(0));

	}
}
