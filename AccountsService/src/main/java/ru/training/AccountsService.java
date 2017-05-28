package ru.training;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ARnikev on 21/05/17.
 */
@Service
public class AccountsService {

	private List<Account> accounts;

	@PostConstruct
	public void init() {
		accounts = new LinkedList<>();
		accounts.add(Account.builder().number("434234123412").userId("ALONOC").build());
		accounts.add(Account.builder().number("441413412344").userId("ALONOC").build());
		accounts.add(Account.builder().number("535454354355").userId("ALON10").build());
		accounts.add(Account.builder().number("625465246246").userId("ALON10").build());
		accounts.add(Account.builder().number("634636346666").userId("ALONTM").build());
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public List<Account> getAccountsByUserId(String userId) {
		return  accounts.stream()
			.filter(account -> userId.equals(account.getUserId()))
			.collect(Collectors.toList());
	}
}
