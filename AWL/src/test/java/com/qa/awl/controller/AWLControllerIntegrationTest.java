package com.qa.awl.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.awl.domain.AWL;
import com.qa.awl.repo.AWLRepo;
import com.qa.awl.service.AWLService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AWLController.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AWLControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private AWLService awlService;

	@Autowired
	private AWLController awlController;

	@Mock
	private AWLRepo awlRepo;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	AWL stub() {
		AWL awl = new AWL();
		awl.setRating(5);
		awl.setName("Death Note");
		awl.setId(1L);
		awl.setEpisode(12);
		return awl;
	}

	@Test
	void cons() {
		AWL awl = new AWL("Death", 12, 5);
		Assert.assertNotNull(awl);
	}

	@Test
	void init() {
		Object object = new Object();
		AWL awl = new AWL();
		awl.setId(1L);
		awl.setName("Death Note");
		awl.setRating(5);
		awl.setEpisode(12);
		awl.equals(stub());
		awl.equals(object);
		awl.hashCode();
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
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/getAll", HttpMethod.GET, entity,
				String.class);
		int res = awlController.getAnimeWatchList().getStatusCodeValue();
		Assert.assertEquals(res, 200);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	void findByNameTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		when(awlService.getAnimeByName(any())).thenReturn(stub());
		String res = awlController.findByName("Death Note").getName();
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/findByName", HttpMethod.GET,
				entity, String.class);
		Assert.assertEquals(res, "Death Note");
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	public void removeAnimeTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/remove/1", HttpMethod.DELETE,
				entity, String.class);
		when(awlService.remove(1L)).thenReturn(true);
		int res = awlController.removeAnime(1L).getStatusCodeValue();
		Assert.assertEquals(res, 204);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	public void updateAnimeListTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/update/1", HttpMethod.PUT,
				entity, String.class);
		when(awlService.update(any(), any())).thenReturn(stub());
		int res = awlController.updateAnimeList(1L, stub()).getStatusCodeValue();
		Assert.assertEquals(res, 202);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	public void getAnimeByIdTest() throws Exception {
		Long id = 1L;
		awlController.createAnimeWatchList(stub());
		when(awlRepo.saveAndFlush(any())).thenReturn(stub());
		awlService.create(stub());
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/getOne/" + id, HttpMethod.GET,
				entity, String.class);
		when(awlService.getByID(any())).thenReturn(stub());
		int res = awlController.getAnimeById(1L).getStatusCodeValue();
		Assert.assertEquals(res, 200);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}
}