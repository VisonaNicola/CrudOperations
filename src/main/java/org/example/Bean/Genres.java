package org.example.Bean;

import java.util.ArrayList;
import java.util.List;
/**
 * Bean for a collection of genres.
 * @author nvisona
 *
 */
public class Genres implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	List<Genre> genres;

	public Genres(List<Genre> genres) {
		super();
		this.genres = genres;
	}

	public Genres() {
		super();
		genres = new ArrayList<>();
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> albums) {
		this.genres = albums;
	}
	
	public void addGenre(Genre genre) {
		genres.add(genre);
	}
	
}
