package ru.training;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by ARnikev on 21/05/17.
 */
@Getter
@Builder
public class Account {

	private String number;
	private String userId;
}
