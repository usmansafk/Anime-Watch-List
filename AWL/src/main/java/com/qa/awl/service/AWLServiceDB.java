package com.qa.awl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.awl.domain.AWL;
import com.qa.awl.repo.AWLRepo;

@Service
public class AWLServiceDB implements AWLService {

	// ----- Instance Variables -----
	private AWLRepo repo; // <-- dependency

	// ----- Constructor -----
	public AWLServiceDB(AWLRepo repo) { // <-- injection
		this.repo = repo;
	}

	// ----- CRUD Methods -----
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
	public AWL update(Long id, AWL updateAnimeInfo) {
		AWL anime = this.repo.findById(id).orElseThrow();
		anime.setName(updateAnimeInfo.getName());
		anime.setEpisode(updateAnimeInfo.getEpisode());
		anime.setRating(updateAnimeInfo.getRating());
		return this.repo.saveAndFlush(anime);
	}

	@Override
	public boolean remove(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);

	}

}
