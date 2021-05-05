package com.qa.awl.service;

import java.util.List;

import com.qa.awl.domain.AWL;

public interface AWLService {
	
	//Template Class
	//Abstract Methods - no method body!
	
	//CRUD OPERATIONS
	
	AWL create(AWL a);
	List<AWL> getAll();
	AWL getByID(int id);
	boolean remove(int id);
	AWL update(int id, AWL newAnime);
	AWL getAnimeByName(String name);

	
	
}
