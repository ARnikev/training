package ru.training;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by ARnikev on 28/05/17.
 */
@FeignClient(name = "accounts-service")
public interface AccountsClient {

	@RequestMapping("/accounts")
	Accounts getUserAccounts(@RequestParam Map<String, String> query);
}
