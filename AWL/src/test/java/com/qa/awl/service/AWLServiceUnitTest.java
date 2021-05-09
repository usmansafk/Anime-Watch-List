package com.qa.awl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.awl.domain.AWL;
import com.qa.awl.repo.AWLRepo;

@SpringBootTest
public class AWLServiceUnitTest {
	
	@Autowired
	private AWLService service; 
	
	@MockBean
	private AWLRepo repo; 
	
	
	// i have done this one and it is working 
	@Test
	void testCreate() {
		// GIVEN 
		
		AWL opm = new AWL("OPM", 25, 5);
		AWL savedOpm = new AWL(1L,"OPM", 25, 5);
		
		// WHEN 
		Mockito.when(this.repo.saveAndFlush(opm)).thenReturn(savedOpm);
		
		// THEN
		assertThat(this.service.create(opm)).isEqualTo(savedOpm);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(opm);

	} 
	


}
