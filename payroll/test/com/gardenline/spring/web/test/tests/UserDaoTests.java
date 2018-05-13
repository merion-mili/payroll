package com.gardenline.spring.web.test.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.gardenline.spring.web.dao.User;
import com.gardenline.spring.web.dao.UsersDao;
@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/gardenline/spring/web/config/dao-context.xml",
		"classpath:com/gardenline/spring/web/config/security-context.xml",
		"classpath:com/gardenline/spring/web/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("merionmili", "Merion Mili", "hellohello",
			"mmili@gmail.com", true, "ROLE_USER");
	private User user2 = new User("mateokuramano", "Mateo Kouramano", "hellothere",
			"mateok@marikaj.com", true, "ROLE_ADMIN");
	private User user3 = new User("vilmazig", "vilma Zigori", "ciaociao",
			"vilmazigori@marikaj.com", true, "ROLE_USER");
	private User user4 = new User("llambidashi", "Llambi Dashi", "ciaociao",
			"llambidashi@marikaj.com", false, "user");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		
		List<User> users1 = usersDao.getAllUsers();
		
		assertEquals("One user should have been created and retrieved", 1, users1.size());
		
		assertEquals("Inserted user should match retrieved", user1, users1.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		List<User> users2 = usersDao.getAllUsers();
		
		assertEquals("Should be four retrieved users.", 4, users2.size());
	}
	
	public void testExists(){
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		assertTrue("User should exist", usersDao.exists(user1.getUsername()));
	}
	
	

	// TODO - Reimplement this
	@Test
	public void testUsers() {
		User user = new User("merionmili", "Merion Mili", "hellohello",
				"mmili@gmail.com", true, "user");

		usersDao.create(user);

		List<User> users = usersDao.getAllUsers();

		assertEquals("Number of users should be 1.", 1, users.size());

		assertTrue("User should exist.", usersDao.exists(user.getUsername()));

		assertEquals("Created user should be identical to retrieved user",
				user, users.get(0));

	}
}