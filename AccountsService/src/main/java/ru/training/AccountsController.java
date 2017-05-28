package ru.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ARnikev on 21/05/17.
 */
@RestController
public class AccountsController {

	@Autowired
	private AccountsService accountsService;

	@RequestMapping(value = "/accounts")
	public Accounts getAccounts(@RequestParam(required = false) String userId) {
		List<Account> accounts;

		if (StringUtils.isEmpty(userId)) {
			accounts = accountsService.getAccounts();
		} else {
			accounts = accountsService.getAccountsByUserId(userId);
		}

		return Accounts.builder()
			.accounts(accounts)
			.build();
	}
}
