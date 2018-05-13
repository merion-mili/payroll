package com.gardenline.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	public boolean exists(String username) {
		User user = getUser(username);
		return user != null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
	}

	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		return (User) crit.uniqueResult();
	}

	public User getUserPerMail(String email) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("email", email));
		return (User) crit.uniqueResult();
	}

	public void saveOrUpdate(int id, User user) {
		User user2 = getUser(id);
		user2.setUsername(user.getUsername());
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		user2.setPassword(passwordEncoder.encode(user.getPassword()));
		user2.setAuthority(user.getAuthority());
		user2.setEnabled(user.isEnabled());
		session().save(user2);
	}

	public void delete(String username) {

		Query query1 = session().createQuery(
				"delete from  User u where u.username=:username");
		query1.setString("username", username);

		query1.executeUpdate();

	}

	public User getUser(int id) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(id));

		return (User) crit.uniqueResult();
	}

	public void update(User user) {
		session().update(user);

	}

	public void delete(User username) {
		session().delete(username);

	}

	public void deleteUser(User user) {
		session().delete(user);

	}

}
