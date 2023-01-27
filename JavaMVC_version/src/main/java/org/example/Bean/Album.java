package org.example.Bean;

/**
 * Bean for an album, characterized by an id and a name.
 * @author nvisona
 *
 */
public class Album implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int albumId;
	private String name;
	public Album(int albumId, String name) {
		super();
		this.albumId = albumId;
		this.name = name;
	}
	public Album() {
		super();
	}
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
