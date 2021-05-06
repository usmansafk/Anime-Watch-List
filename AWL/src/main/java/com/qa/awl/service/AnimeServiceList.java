package com.qa.awl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.awl.domain.AWL;

public class AnimeServiceList implements AWLService {

	
	//THIS FILE IS NOW NO LONGER IN USE!!!!!
	
	private List<AWL> animeWatchList; // <--- dependency
	
	public AnimeServiceList(List<AWL> animeList) {
		this.animeWatchList = animeList;
	}
	
	@Override
	public AWL create(AWL a) {
		this.animeWatchList.add(a);
		AWL added = this.animeWatchList.get(this.animeWatchList.size()-1);
		return added;
	}

	@Override
	public List<AWL> getAll() {
		return this.animeWatchList;
	}

	@Override
	public AWL getByID(int id) {
		return this.animeWatchList.get(id);
	}

	@Override
	public boolean remove(int id) {
		this.animeWatchList.remove(id);
		return true;
	}

	@Override
	public AWL update(int id, AWL newAnime) {
		
		return null;
	}

	@Override
	public AWL getAnimeByName(String name) {
	
		return null;
	}


}
