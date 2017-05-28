package ru.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ARnikev on 28/05/17.
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService  usersService;

	@RequestMapping("/users/{userId}")
	public User getUser(@PathVariable String userId) {
		return usersService.getUserById(userId);
	}

}
