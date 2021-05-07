package com.qa.awl.service;

import java.util.List;

import com.qa.awl.domain.AWL;

public interface AWLService {

	// ----- Template Class -----
	// ----- CRUD OPERATIONS -----
	AWL create(AWL a);

	List<AWL> getAll();

	AWL getByID(Long id);

	boolean remove(Long id);

	AWL update(Long id, AWL newAnime);

	AWL getAnimeByName(String name);

}
