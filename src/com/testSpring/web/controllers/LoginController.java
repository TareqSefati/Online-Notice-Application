package com.testSpring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.testSpring.web.dao.FormValidationGroup;
import com.testSpring.web.dao.User;
import com.testSpring.web.service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	// Login Form page showing method
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	// Denied page showing method
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}

	// Logged out page showing method
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	// New Account Form page showing method
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	// @RequestMapping("/createaccount")
	// public String createAccount() {
	// return "accountcreated";
	// }

	// After submitting the New Account Form page handler method
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String doCreateUser(@Validated(FormValidationGroup.class) User user, BindingResult result) {

		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		try {
			usersService.createUser(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		return "accountcreated";
	}

	// Showing the admin page method
	@RequestMapping("/admin")
	public String showAdmin(Model model) {

		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);

		return "admin";
	}

}
