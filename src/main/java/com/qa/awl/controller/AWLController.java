package com.qa.awl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.awl.domain.AWL;
import com.qa.awl.service.AWLService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AWLController {

	// ----- Instance Variables -----
	private AWLService service;

	// ----- Constructor -----
	@Autowired
	public AWLController(AWLService service) {
		this.service = service;
	}

	// ----- CRUD Controller Mappings -----

	// CREATE
	@PostMapping("/create")
	public ResponseEntity<AWL> createAnimeWatchList(@RequestBody AWL anime) {
		return new ResponseEntity<>(this.service.create(anime), HttpStatus.CREATED);
	}

	// READ (all)
	@GetMapping("/getAll")
	public ResponseEntity<List<AWL>> getAnimeWatchList() {
		return ResponseEntity.ok(this.service.getAll());
	}

	// UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<AWL> updateAnimeList(@PathVariable Long id, @RequestBody AWL newAnimeObject) {
		return new ResponseEntity<AWL>(this.service.update(id, newAnimeObject), HttpStatus.ACCEPTED);
	}

	// DELETE
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Boolean> removeAnime(@PathVariable Long id) { // NOTE: this is the index position of the id
		return this.service.remove(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
