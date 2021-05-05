package com.qa.awl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.awl.domain.AWL;
import com.qa.awl.service.AWLService;

@RestController
public class AWLController {
	
//	testing.... delete later.
//	@GetMapping("/test") //Type of Request
//	public String test() {
//		return "Woo, it works";
//	}
	
	private AWLService service;
	
	public AWLController(AWLService service) {
		this.service = service;
	}
	
	//---CRUD---
	
	//CREATE
	@PostMapping("/create")
	public ResponseEntity<AWL> createAnimeWatchList(@RequestBody AWL anime) {
		
		return new ResponseEntity<>(this.service.create(anime), HttpStatus.CREATED);
	}
	
	//READ (all)
	@GetMapping("/getAll")
	public ResponseEntity<List<AWL>> getAnimeWatchList(){
		return ResponseEntity.ok(this.service.getAll());
	}
	
	//READ(one)
	@GetMapping("/getOne/{id}")
	public ResponseEntity<AWL> getAnimeById(@PathVariable int id) { //NOTE: this is the index position of the id
		return ResponseEntity.ok(this.service.getByID(id));
	}
	
	//Custom~ Find Anime by Name
	@GetMapping("/findByName") 
	public AWL findByName(@PathParam("name") String name) {
		for(AWL a : this.service.getAll()) {
			System.out.println(a.getName());
		}
		System.out.println(name);
		return null;
	}
	
	//UPDATE ------------REVISIT THIS.
	@PutMapping("/update/{id}")
	public AWL updateAnimeList(@PathVariable int id) {//NOTE: this is the index position of the id
		return null;
	}
	
	
	//DELETE
	@DeleteMapping("/remove/{id}")
	public AWL removeAnime(@PathVariable int id) { //NOTE: this is the index position of the id
		this.service.remove(id);
		return this.service.getByID(id);
	}
	
	

}
