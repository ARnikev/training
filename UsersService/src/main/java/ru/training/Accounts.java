package ru.training;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by ARnikev on 28/05/17.
 */
@Data
@AllArgsConstructor
public class Accounts {

	private List<Account> accounts;

}
