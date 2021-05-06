package com.qa.awl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AWL {

	// ----Attributes----
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-increments
	private Long id;
	private String name;
	private int episode;
	private int rating;

	public Long getId() {
		return id;
	}

	// ----Constructors----
	public AWL(String name, int episode, int rating) {
		super();
		this.name = name;
		this.episode = episode;
		this.rating = rating;
	}
	
	public AWL(Long id, String name, int episode, int rating) {
		super();
		this.id = id;
		this.name = name;
		this.episode = episode;
		this.rating = rating;
	}

	public AWL() {}

	// ----Methods----
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
