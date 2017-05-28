package ru.training;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ARnikev on 21/05/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsControllerTest {

	@Autowired
	protected WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void shouldReturnAllAccounts() throws Exception {
		mockMvc.perform(post("/accounts")).andExpect(status().isOk())
			.andExpect(jsonPath("$.accounts").isArray())
			.andExpect(jsonPath("$.accounts[0].number").value("434234123412"))
			.andExpect(jsonPath("$.accounts[1].number").value("441413412344"))
			.andExpect(jsonPath("$.accounts[2].number").value("535454354355"))
			.andExpect(jsonPath("$.accounts[3].number").value("625465246246"))
			.andExpect(jsonPath("$.accounts[4].number").value("634636346666"))
			.andExpect(jsonPath("$.accounts[0].userId").value("ALONOC"))
			.andExpect(jsonPath("$.accounts[1].userId").value("ALONOC"))
			.andExpect(jsonPath("$.accounts[2].userId").value("ALON10"))
			.andExpect(jsonPath("$.accounts[3].userId").value("ALON10"))
			.andExpect(jsonPath("$.accounts[4].userId").value("ALONTM"));
	}

	@Test
	public void shouldReturnAccountsByUserId() throws Exception {
		mockMvc.perform(post("/accounts?userId=ALON10")).andExpect(status().isOk())
			.andExpect(jsonPath("$.accounts[0].number").value("535454354355"))
			.andExpect(jsonPath("$.accounts[1].number").value("625465246246"))
			.andExpect(jsonPath("$.accounts[0].userId").value("ALON10"))
			.andExpect(jsonPath("$.accounts[1].userId").value("ALON10"));
	}

}