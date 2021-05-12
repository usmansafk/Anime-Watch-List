package com.qa.awl.service;

import com.qa.awl.domain.AWL;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;

import com.qa.awl.repo.AWLRepo;

import java.util.*;

@SpringBootTest
public class AWLServiceUnitTest {

	@InjectMocks
	private AWLService service;

	@Mock
	private AWLRepo repo;

	AWL stub() {
		AWL awl = new AWL();
		awl.setEpisode(1);
		awl.setId(1L);
		awl.setName("opm");
		awl.setRating(1);
		return awl;
	}

	
	
	@Test
	void getAllTest() {

		List<AWL> list = new ArrayList<>();
		list.add(0, stub());
		list.add(1, stub());
		when(repo.findAll()).thenReturn(list);
		Assert.assertEquals(service.getAll().get(0).getName(), "opm");
	}

	@Test
	void createTest() {
	
		when(this.repo.saveAndFlush(any())).thenReturn(stub());
		AWL id = this.service.create(stub());
		Assert.assertEquals(id.getRating(), 1);
//		Assert.assertEquals(this.repo.equals(id), stub());
		
//		AWL anime = new AWL(1L,"opm",1,1);
//		AWL savedAnime = new AWL(1L, "opm", 1, 1);
//		Mockito.when(this.repo.save(anime).isEqualTo(savedAnime));
//		Mockito.verify(this.repo, Mockito.times(1).save(anime));

		
		
	}

	@Test
	void getByIdTest() {
		Optional<AWL> awlOptional = Optional.ofNullable(stub());
		when(repo.findById(1L)).thenReturn(awlOptional);
		AWL awl = service.getByID(1l);
		verify(repo, times(1)).findById(any());
	}

	@Test
	void getAnimeByNameTest() {
		when(repo.findByName("opm")).thenReturn(stub());
		AWL awl = service.getAnimeByName("opm");
		Assert.assertEquals(awl.getEpisode(), 1);
	}

	@Test
	void updateTest() {
		Optional<AWL> awl = Optional.of(Optional.ofNullable(stub()).get());
		when(repo.findById(any())).thenReturn(awl);
		when(repo.saveAndFlush(any())).thenReturn(stub());
		AWL response = service.update(1L, stub());
		Assert.assertEquals(response.getName(), "opm");
		  
	}
 
	@Test
	void removeTest() {
		when(service.remove(1l)).thenReturn(true);
		verify(repo, times(1)).deleteById(anyLong());
	}

}
