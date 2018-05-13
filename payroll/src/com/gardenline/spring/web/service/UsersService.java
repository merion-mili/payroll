package com.gardenline.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.User;
import com.gardenline.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDao usersDao;

	public void create(User user) {
		usersDao.create(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);

	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}

	public User getUser(String username) {
		return usersDao.getUser(username);
	}

	public void saveOrUpdate(int id, User user) {
		usersDao.saveOrUpdate(id, user);

	}

	public void delete(User username) {
		usersDao.delete(username);

	}

	public void updateUser(User user) {
		usersDao.update(user);

	}

	public void delete(String username) {
		usersDao.delete(username);

	}

	public User getUser(int id) {
		return usersDao.getUser(id);
	}

	public void deleteUser(User user) {
		usersDao.deleteUser(user);

	}

	public User getUserPermail(String email) {
		return usersDao.getUserPerMail(email);

	}

}
