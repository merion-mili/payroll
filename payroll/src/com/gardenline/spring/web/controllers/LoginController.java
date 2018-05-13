package com.gardenline.spring.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gardenline.spring.web.dao.FormValidationGroup;
import com.gardenline.spring.web.dao.User;
import com.gardenline.spring.web.service.UsersService;

@Controller
public class LoginController {

	UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model) {
		if (error != null) {
			model.addAttribute("error", "Invalid username and password");
		}
		if (logout != null) {
			model.addAttribute("msg", "You have been logout succesfully");
		}
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {

		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);

		return "admin";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";

	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createaccount(
			@ModelAttribute("user") @Validated(FormValidationGroup.class) User user,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setEnabled(true);

		if (usersService.exists(user.getUsername())) {
			System.out.println("Caught duplicate username");
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username",
					"This username already exist");
			return "newaccount";
		}

		return "accountcreated";
	}

	@RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, ModelMap modelMap) {

		modelMap.addAttribute("user", usersService.getUser(id)); // String

		return "editUser";
	}

	@RequestMapping(value = "/editUser/{id}", method = RequestMethod.POST)
	public String editOfferPost(
			@PathVariable("id") int id,
			@Validated(value = FormValidationGroup.class) @ModelAttribute(value = "user") User user,
			BindingResult result) {

		if (result.hasErrors()) {
			return "editUser";
		}

		usersService.saveOrUpdate(id, user);

		return "redirect:/admin";
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id, ModelMap model,
			HttpServletRequest request) {

		User user = usersService.getUser(id);
		usersService.delete(user);
		return "redirect:/admin";
	}

}
