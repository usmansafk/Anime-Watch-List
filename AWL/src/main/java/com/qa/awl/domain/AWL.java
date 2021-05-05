package com.qa.awl.domain;

public class AWL {

	// ----Attributes----
	private Long id;
	private String name;
	private int episode;
	private int rating;

	public Long getId() {
		return id;
	}

	// ----Constructors----
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
