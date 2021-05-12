package com.qa.awl.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.qa.awl.repo.AWLRepo;
import com.qa.awl.service.AWLService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.awl.domain.AWL;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AWLController.class)
@AutoConfigureMockMvc
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
	private  AWLRepo awlRepo;
	
	@Test
	void init() {
		AWL awl = new AWL();
		awl.equals(stub());
		awl.hashCode();
	}

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();


//	//@Test
//	void testCharacter() throws Exception {
//
//		// Create Anime
//		AWL anime = new AWL("Death Note", 12, 5);
//
//		// Convert to JSON String
//		String animeAsJSON = this.mapper.writeValueAsString(anime);
//
//		// Build our mock Request
//		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(animeAsJSON);
//
//		// Create "saved" anime
//		AWL savedAnime = new AWL(2L, "Death Note", 12, 5);
//
//		// Convert "saved" POJO to JSON
//		String savedAnimeAsJSON = this.mapper.writeValueAsString(savedAnime);
//
//		// Check status is 201 - CREATED
//		ResultMatcher matchStatus = status().isCreated();
//
//		// Check that the response body is correct
//		ResultMatcher matchBody = content().json(savedAnimeAsJSON);
//
//		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
//
//	}

	@Test
	void createAnimeWatchListTest() throws Exception {
		MvcResult mvcResult = mockMVC.perform(post("/create")
				.contentType("application/json"))
				.andReturn();
		AWL anime = new AWL(1L,"Death Note", 12, 5);
		String expectedResponseBody = mapper.writeValueAsString(anime);
		assertNotNull(mvcResult.getHandler());
	}

	@Test
	void getAllTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(),headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:9092/getAll",
				HttpMethod.GET, entity, String.class);
		int res = awlController.getAnimeWatchList().getStatusCodeValue();
		Assert.assertEquals(res,200);
		Assert.assertNotEquals(response.getStatusCodeValue(),404);
	}

	@Test
	void findByNameTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(),headers);
		when(awlService.getAnimeByName(any())).thenReturn(stub());
		String res = awlController.findByName("usman").getName();
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:9092/findByName",
				HttpMethod.GET, entity, String.class);
		Assert.assertEquals(res,"usman");
		Assert.assertNotEquals(response.getStatusCodeValue(),404);
	}

	@Test
	public void removeAnimeTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(),headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:9092/remove/1",
				HttpMethod.DELETE, entity, String.class);
		when(awlService.remove(1L)).thenReturn(true);
		int res = awlController.removeAnime(1L).getStatusCodeValue();
		Assert.assertEquals(res,204);
		Assert.assertNotEquals(response.getStatusCodeValue(),404);
	}

	@Test
	public void updateAnimeListTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(),headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:9092/update/1",
				HttpMethod.PUT, entity, String.class);
		when(awlService.update(any(),any())).thenReturn(stub());
		int res = awlController.updateAnimeList(1L,stub()).getStatusCodeValue();
		Assert.assertEquals(res,202);
		Assert.assertNotEquals(response.getStatusCodeValue(),404);
	}

	@Test
	public void getAnimeByIdTest() throws Exception {
		Long id = 1L;


		awlController.createAnimeWatchList(stub());
		when(awlRepo.saveAndFlush(any())).thenReturn(stub());
		awlService.create(stub());
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(),headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:9092/getOne/"+id,
				HttpMethod.GET, entity, String.class);
		when(awlService.getByID(any())).thenReturn(stub());
		 int res = awlController.getAnimeById(1L).getStatusCodeValue();
		 Assert.assertEquals(res,200);
		Assert.assertNotEquals(response.getStatusCodeValue(),404);
	}


	AWL stub(){
		AWL awl = new AWL();
		awl.setRating(1);
		awl.setName("usman");
		awl.setId(1L);
		awl.setEpisode(1);
		return  awl;
	}
	
	@Test
	void cons() {
		AWL awl = new AWL("opm",1,1);
		Assert.assertNotNull(awl);
	}
}