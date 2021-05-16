package com.qa.awl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
public class AWL {

	// ----Attributes----
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments
	private Long id;

	@NotNull(message = "The name of the anime must be entered")
	@Column(unique = true)
	private String name;
	private int episode;
	private int rating;

	public Long getId() {
		return id;
	}

	// ----Constructor----
	public AWL(String name, int episode, int rating) {
		super();
		this.name = name;
		this.episode = episode;
		this.rating = rating;
	}

}