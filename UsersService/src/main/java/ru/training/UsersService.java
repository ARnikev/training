package ru.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ARnikev on 28/05/17.
 */
@Service
public class UsersService {

	private List<User> users;

	@Autowired
	private AccountsClient accountsClient;

	@PostConstruct
	public void init() {
		users = new ArrayList<>();
		users.add(new User("ALONOC", "Petrov Petr Petrovich"));
		users.add(new User("ALON10", "Pupkin Vasiliy Vasilievich"));
	}

	public User getUserById(String userId) {
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);

		Accounts accounts = accountsClient.getUserAccounts(params);

		return users.stream()
			.filter(user -> userId.equals(user.getId()))
			.findAny()
			.map(user -> user.setAccounts(accounts.getAccounts()))
			.orElseThrow(() -> new RuntimeException("user with id " + userId + " is not found"));
	}
}
