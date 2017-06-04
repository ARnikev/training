package ru.training;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.print.DocFlavor;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMap;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ARnikev on 28/05/17.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UsersControllerTest {

	private MockMvc mockMvc;

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private AccountsClient accountsClient;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(documentationConfiguration(restDocumentation))
			.build();

		given(accountsClient.getUserAccounts(anyMap()))
			.willReturn(
				new Accounts(Arrays.asList(
					new Account("434234123412", "ALONOC"),
					new Account("441413412344", "ALONOC")
				))
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
			.andExpect(jsonPath("$.accounts[1].userId").value("ALONOC"))
			.andDo(getDocsResultHandler());
	}

	public static RestDocumentationResultHandler getDocsResultHandler() {
		return  document(
			"{method-name}",
			preprocessRequest(prettyPrint()),
			preprocessResponse(prettyPrint()),
			responseFields(
				fieldWithPath("id").type(JsonFieldType.STRING).description("CUS клиента"),
				fieldWithPath("fio").type(JsonFieldType.STRING).description("FIO клиента"),
				fieldWithPath("accounts[]").type(JsonFieldType.ARRAY).description("Счета клиента"),
				fieldWithPath("accounts[].userId").type(JsonFieldType.STRING).description("Идентификатор счета"),
				fieldWithPath("accounts[].number").type(JsonFieldType.STRING).description("Номер счета")
			)
		);
	}
}