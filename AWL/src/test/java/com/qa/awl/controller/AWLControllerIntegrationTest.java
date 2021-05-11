
package com.qa.awl.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.awl.domain.AWL;
import com.qa.awl.repo.AWLRepo;
import com.qa.awl.service.AWLService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AWLController.class)
//@Sql(scripts = {"classpath:schema.sql","classpath:data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class AWLControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private AWLService awlService;

	@InjectMocks
	private AWLController awlController;

	@Mock
	private AWLRepo awlRepo;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	void testAnime() throws Exception {
		AWL anime = new AWL("Death Note", 12, 5);
		String animeAsJSON = this.mapper.writeValueAsString(anime);
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(animeAsJSON);
		AWL savedAnime = new AWL(1L, "Death Note", 12, 5);
		String savedAnimeAsJSON = this.mapper.writeValueAsString(savedAnime);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedAnimeAsJSON);
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	@Test
	void createAnimeWatchListTest() throws Exception {
		MvcResult mvcResult = mockMVC.perform(post("/create").contentType("application/json")).andReturn();
		AWL anime = new AWL(1L, "Death Note", 12, 5);
		String expectedResponseBody = mapper.writeValueAsString(anime);
		assertNotNull(mvcResult.getHandler());
	}

	@Test
	void getAllTest() throws Exception {
		AWL awl = new AWL();
		awl.setEpisode(1);
		awl.setId(1L);
		awl.setName("usman");
		awl.setRating(1);
		HttpEntity<AWL> entity = new HttpEntity<AWL>(awl, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/getAll", HttpMethod.GET, entity,
				String.class);
		//Assert.assertNotEquals(response.getStatusCodeValue(), 404);
		System.out.println(response.getStatusCode());
	}

	@Test
	void findByNameTest() throws Exception {
		AWL awl = new AWL();
		awl.setEpisode(1);
		awl.setId(1L);
		awl.setName("usman");
		awl.setRating(1);
		HttpEntity<AWL> entity = new HttpEntity<AWL>(awl, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/findByName", HttpMethod.GET,
				entity, String.class);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	public void removeAnimeTest() throws Exception {
		AWL awl = new AWL();
		awl.setEpisode(1);
		awl.setId(1L);
		awl.setName("usman");
		awl.setRating(1);
		awlController.removeAnime(1L);
		HttpEntity<AWL> entity = new HttpEntity<AWL>(awl, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/remove/1", HttpMethod.DELETE,
				entity, String.class);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	public void updateAnimeListTest() throws Exception {
		AWL awl = new AWL();
		awl.setEpisode(1);
		awl.setId(1L);
		awl.setName("usman");
		awl.setRating(1);
		HttpEntity<AWL> entity = new HttpEntity<AWL>(awl, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/update/1", HttpMethod.PUT,
				entity, String.class);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	public void getAnimeByIdTest() throws Exception {
		AWL awl = new AWL();
		awl.setEpisode(1);
		awl.setId(1L);
		awl.setName("usman");
		awl.setRating(1);
		Long id = 1L;
		when(awlRepo.saveAndFlush(any())).thenReturn(awl);
		awlService.create(awl);
		HttpEntity<AWL> entity = new HttpEntity<AWL>(awl, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/getOne/" + id, HttpMethod.GET,
				entity, String.class);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

}
