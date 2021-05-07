package com.qa.awl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.awl.domain.AWL;
import com.qa.awl.repo.AWLRepo;


@Service
public class AWLServiceDB implements AWLService{
	
	private AWLRepo repo; // <-- dependency
	
	public AWLServiceDB(AWLRepo repo) { // <-- injection
		this.repo = repo;
	} 
	
	@Override
	public AWL create(AWL a) {
		return this.repo.saveAndFlush(a);
	}

	@Override
	public List<AWL> getAll() {
		return this.repo.findAll();
	}

	@Override
	public AWL getByID(Long id) {
		Optional<AWL> optionalAnime = this.repo.findById(id);
		return optionalAnime.get();
	}
	
	@Override
	public AWL getAnimeByName(String name) {
		return this.repo.findByName(name);
	}

	@Override
	public AWL update(Long id, AWL newAnime) {
		AWL anime = this.repo.findById(id).orElseThrow();
		anime.setName(newAnime.getName());
		anime.setEpisode(newAnime.getEpisode());
		anime.setRating(newAnime.getRating());
		return this.repo.saveAndFlush(anime);
	}
	
	@Override
	public boolean remove(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	
	}

}
