package org.example.Bean;
/**
 * Bean for a genre, characterized by an id and a name.
 * @author nvisona
 *
 */
public class Genre implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private int genreId;
	private String name;
	public Genre(int genreId, String name) {
		super();
		this.genreId = genreId;
		this.name = name;
	}
	public Genre() {
		super();
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
