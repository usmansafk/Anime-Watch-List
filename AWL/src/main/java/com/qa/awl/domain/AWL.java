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

	// ----Constructors----
	public AWL(String name, int episode, int rating) {
		super();
		this.name = name;
		this.episode = episode;
		this.rating = rating;
	}

//	public AWL(Long id, String name, int episode, int rating) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.episode = episode;
//		this.rating = rating;
//	}
//
//	public AWL() {
//	}

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

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + episode;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + rating;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		AWL other = (AWL) obj;
//		if (episode != other.episode)
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		if (rating != other.rating)
//			return false;
//		return true;
//	}

}
