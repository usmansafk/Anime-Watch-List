
package com.qa.awl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.awl.domain.AWL;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema.sql", "classpath:data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class AWLControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCharacter() throws Exception {

		// Create Anime
		AWL anime = new AWL("Death Note", 12, 5);

		// Convert to JSON String
		String animeAsJSON = this.mapper.writeValueAsString(anime);

		// Build our mock Request
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(animeAsJSON);

		// Create "saved" anime
		AWL savedAnime = new AWL(2L, "Death Note", 12, 5);

		// Convert "saved" POJO to JSON
		String savedAnimeAsJSON = this.mapper.writeValueAsString(savedAnime);

		// Check status is 201 - CREATED
		ResultMatcher matchStatus = status().isCreated();

		// Check that the response body is correct
		ResultMatcher matchBody = content().json(savedAnimeAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);

	}

}
