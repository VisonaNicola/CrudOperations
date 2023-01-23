package org.example.Bean;

import java.util.ArrayList;
import java.util.List;
/**
 * Bean for a collection of albums.
 * @author nvisona
 *
 */
public class Albums implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	List<Album> albums;

	public Albums(List<Album> albums) {
		super();
		this.albums = albums;
	}

	public Albums() {
		super();
		albums = new ArrayList<>();
		}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	public void addAlbum(Album a) {
		albums.add(a);
	}
	
}
