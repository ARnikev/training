package ru.training;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ARnikev on 28/05/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private AccountsClient accountsClient;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

		given(accountsClient.getUserAccounts(anyMap()))
			.willReturn(
				Arrays.asList(
					new Account("434234123412", "ALONOC"),
					new Account("441413412344", "ALONOC")
				)
			);
	}

	@Test
	public void shouldReturnUserWithAccounts() throws Exception {
		mockMvc.perform(post("/users/ALONOC")).andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value("ALONOC"))
			.andExpect(jsonPath("$.fio").value("Petrov Petr Petrovich"))
			.andExpect(jsonPath("$.accounts").isArray())
			.andExpect(jsonPath("$.accounts[0].number").value("434234123412"))
			.andExpect(jsonPath("$.accounts[0].userId").value("ALONOC"))
			.andExpect(jsonPath("$.accounts[1].number").value("441413412344"))
			.andExpect(jsonPath("$.accounts[1].userId").value("ALONOC"));
	}
}