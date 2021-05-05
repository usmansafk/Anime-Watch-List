package com.qa.awl.domain;

public class AWL {
	
	//----Attributes----
	private Long id;
	private String name;
	private Integer episode;
	private Integer rating;
	public Long getId() {
		return id;
	}
	
	public AWL(Long id, String name, Integer episode, Integer rating) {
		super();
		this.id = id;
		this.name = name;
		this.episode = episode;
		this.rating = rating;
	}
	
	public AWL() {}


	//----Methods----
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEpisode() {
		return episode;
	}
	public void setEpisode(Integer episode) {
		this.episode = episode;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
	
	
	

}
