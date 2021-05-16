package com.qa.awl.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.awl.AwlApplication;
import com.qa.awl.domain.AWL;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AwlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AWLControllerIntegrationTest {

	// @Autowired
	// private MockMvc mockMVC;
	private String staticURL = "http://localhost:";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	public HttpHeaders httpHeaders;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		httpHeaders = new HttpHeaders();
	}

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
		awl.equals(stub());
		awl.equals(object);
		awl.hashCode();
	}

	@Test
	void createAnimeWatchListTest() throws Exception {
		String URI = "/create";
		AWL awl = stub();

		HttpEntity<AWL> httpEntity = new HttpEntity<AWL>(awl, httpHeaders);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange(getCompleteEndPoint(URI), HttpMethod.POST,
				httpEntity, String.class);
		String responseOutput = responseEntity.getBody();
		assertNotNull(responseOutput, "This should not be null");

	}

	@Test
	public void updateAnimeListTest() throws Exception {

		String URI1 = "/create";
		AWL awl = stub();
		HttpEntity<AWL> httpEntity1 = new HttpEntity<AWL>(awl, httpHeaders);
		testRestTemplate.exchange(getCompleteEndPoint(URI1), HttpMethod.POST, httpEntity1, String.class);
		String jsonInput = this.converttoJson(awl);

		String URI2 = "/update/1";
		HttpEntity<AWL> httpEntity2 = new HttpEntity<>(awl, httpHeaders);
		Map<String, String> param = new HashMap<>();
		param.put("episode", "12");
		HttpEntity<AWL> response = testRestTemplate.exchange(getCompleteEndPoint(URI2), HttpMethod.PUT, httpEntity2,
				AWL.class, param);
		AWL awl1 = response.getBody();
		String jsonOutput = this.converttoJson(awl1);
		System.out.println("jsonInput---->" + jsonInput);
		System.out.println("jsonOutput---->" + jsonOutput);
		Assert.assertEquals(awl1.getEpisode(), 12);
	}

	public String getCompleteEndPoint(String URI) {
		System.out.println("Complete URL--->" + (staticURL + port + URI));
		return staticURL + port + URI;
	}

	public String converttoJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}

	@Test
	void getAllTest() throws Exception {
		String URI3 = "/getAll";
		ResponseEntity<String> response = testRestTemplate.exchange(getCompleteEndPoint(URI3), HttpMethod.GET, null,
				String.class);
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	public void removeAnimeTest() throws Exception {
		String URI5 = "/remove/1";
		ResponseEntity<String> response = testRestTemplate.exchange(getCompleteEndPoint(URI5), HttpMethod.DELETE, null,
				String.class);
		Assert.assertEquals(response.getStatusCodeValue(), 204);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	@Test
	public void removeAnimeTestServerError() throws Exception {
		String URI5 = "/remove/-1";
		ResponseEntity<String> response = testRestTemplate.exchange(getCompleteEndPoint(URI5), HttpMethod.DELETE, null,
				String.class);
		Assert.assertEquals(response.getStatusCodeValue(), 500);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

}