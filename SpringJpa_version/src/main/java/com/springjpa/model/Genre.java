package com.springjpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Entity
@Table(name = "genres")
public class Genre {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GenreId")
	private int genreId;
	
	@Column(name="Name")
	private String name;

	
	
	public Genre() {
		super();
	}

	public Genre(int genreId, String name) {
		super();
		this.genreId = genreId;
		this.name = name;
	}
	
	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
