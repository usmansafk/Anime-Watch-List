package com.qa.awl.controller;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.awl.AwlApplication;
import com.qa.awl.domain.AWL;
import com.qa.awl.repo.AWLRepo;
import com.qa.awl.service.AWLService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AwlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AWLControllerIntegrationTest {

	//@Autowired
	//private MockMvc mockMVC;
	private String staticURL = "http://localhost:";

    @LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate testRestTemplate;

    public HttpHeaders httpHeaders;

	@Autowired
	private ObjectMapper mapper;

	//@MockBean
	//private AWLService awlService;

	//@Autowired
	//private AWLController awlController;

	//@Mock
	//private AWLRepo awlRepo;

	//TestRestTemplate restTemplate = new TestRestTemplate();
	//HttpHeaders headers = new HttpHeaders();
	
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
		//awl.setEpisode(12);
		awl.equals(stub());
		awl.equals(object);
		awl.hashCode();
	}
	
	@Test
	void createAnimeWatchListTest() throws Exception {
		//MvcResult mvcResult = mockMVC.perform(post("/create").contentType("application/json")).andReturn();
		//AWL anime = new AWL(1L, "Death Note", 12, 5);
		//String expectedResponseBody = mapper.writeValueAsString(anime);
		//assertNotNull(mvcResult.getHandler());
		
		String URI = "/create";
        AWL awl = stub();
		String jsonInput = this.converttoJson(awl);
		
		HttpEntity<AWL> httpEntity = new HttpEntity<AWL>(awl,httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(getCompleteEndPoint(URI), HttpMethod.POST, httpEntity, String.class);
        String responseOutput = responseEntity.getBody();
        //assertThat(responseOutput).isEqualTo(jsonInput);
        assertNotNull(responseOutput, "This should not be null");
		
	}
	
	
	@Test
	public void updateAnimeListTest() throws Exception {
		//HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		//ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/update/1", HttpMethod.PUT,
		//		entity, String.class);
		//when(awlService.update(any(), any())).thenReturn(stub());
		//int res = awlController.updateAnimeList(1L, stub()).getStatusCodeValue();
		//Assert.assertEquals(res, 202);
		//Assert.assertNotEquals(response.getStatusCodeValue(), 404);
		
		String URI1 = "/create";
		AWL awl = stub();
		HttpEntity<AWL> httpEntity1 = new HttpEntity<AWL>(awl, httpHeaders);
		testRestTemplate.exchange(getCompleteEndPoint(URI1), HttpMethod.POST, httpEntity1, String.class);
		String jsonInput = this.converttoJson(awl);
		
		String URI2 = "/update/1";
		HttpEntity<AWL> httpEntity2 = new HttpEntity<>(awl,httpHeaders);
        Map<String,String> param = new HashMap<>();
        param.put("episode", "12");   
        HttpEntity<AWL> response = testRestTemplate.exchange(getCompleteEndPoint(URI2),HttpMethod.PUT, httpEntity2, AWL.class,param);
		AWL awl1 = response.getBody();
		String jsonOutput = this.converttoJson(awl1);
		System.out.println("jsonInput---->"+ jsonInput);
        System.out.println("jsonOutput---->"+ jsonOutput);
        Assert.assertEquals(awl1.getEpisode(), 12);
	}
	
	public String getCompleteEndPoint(String URI){
        System.out.println("Complete URL--->" + (staticURL + port + URI));
        return staticURL + port + URI;
    }
	
	public String converttoJson(Object object) throws JsonProcessingException {
        //ObjectMapper objectMapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
	@Test
	void getAllTest() throws Exception {
		String URI3 = "/getAll";
		//HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), httpHeaders);
		ResponseEntity<String> response = testRestTemplate.exchange(getCompleteEndPoint(URI3), HttpMethod.GET, null,
				String.class);
		//int res = awlController.getAnimeWatchList().getStatusCodeValue();
		Assert.assertEquals(response.getStatusCodeValue(), 200);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}
//	
//	@Test
//	void findByNameTest() throws Exception {
//		String URI4= "/findByName";
//		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), httpHeaders);
//		//when(awlService.getAnimeByName(any())).thenReturn(stub());
//		//String res = awlController.findByName("Death Note").getName();
//		ResponseEntity<AWL> response = testRestTemplate.exchange(getCompleteEndPoint(URI4), HttpMethod.GET,
//				null, AWL.class);
//		Assert.assertEquals(response.getBody().getName(), "Death Note");
//		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
//	}
	
	@Test
	public void removeAnimeTest() throws Exception {
		String URI5= "/remove/1";
		//HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), httpHeaders);
		ResponseEntity<String> response = testRestTemplate.exchange(getCompleteEndPoint(URI5), HttpMethod.DELETE,
				null, String.class);
		//when(awlService.remove(1L)).thenReturn(true);
		//int res = awlController.removeAnime(1L).getStatusCodeValue();
		//System.out.println("jsonInput---->"+ response.getBody());
		Assert.assertEquals(response.getStatusCodeValue(), 204);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}
	
	@Test
	public void removeAnimeTestServerError() throws Exception {
		String URI5= "/remove/-1";
		//HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), httpHeaders);
		ResponseEntity<String> response = testRestTemplate.exchange(getCompleteEndPoint(URI5), HttpMethod.DELETE,
				null, String.class);
		//when(awlService.remove(1L)).thenReturn(true);
		//int res = awlController.removeAnime(1L).getStatusCodeValue();
		//System.out.println("jsonInput---->"+ response.getBody());
		Assert.assertEquals(response.getStatusCodeValue(), 500);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}  
	



	//@Test
	/*void getAllTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/getAll", HttpMethod.GET, entity,
				String.class);
		int res = awlController.getAnimeWatchList().getStatusCodeValue();
		Assert.assertEquals(res, 200);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	//@Test
	void findByNameTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		when(awlService.getAnimeByName(any())).thenReturn(stub());
		String res = awlController.findByName("Death Note").getName();
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/findByName", HttpMethod.GET,
				entity, String.class);
		Assert.assertEquals(res, "Death Note");
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	//@Test
	public void removeAnimeTest() throws Exception {
		HttpEntity<AWL> entity = new HttpEntity<AWL>(stub(), headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9092/remove/1", HttpMethod.DELETE,
				entity, String.class);
		when(awlService.remove(1L)).thenReturn(true);
		int res = awlController.removeAnime(1L).getStatusCodeValue();
		Assert.assertEquals(res, 204);
		Assert.assertNotEquals(response.getStatusCodeValue(), 404);
	}

	

	//@Test
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
	} */
}