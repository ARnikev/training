package ru.training;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by ARnikev on 28/05/17.
 */
@Data
@Accessors(chain = true)
public class User {

	private String id;
	private String fio;
	private List<Account> accounts;

	public User(String id, String fio) {
		this.id = id;
		this.fio = fio;
	}
}
