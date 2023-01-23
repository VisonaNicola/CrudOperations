package org.example.Bean;

import java.util.Objects;
/**
 * Bean for a track, characterized by an id, a name, a genre and a composer.
 * @author nvisona
 *
 */
public class Track implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private int trackid;
	private String name, album, genre, composer;
	
	public Track() {
		super();
	}

	public Track(int trackid, String name, String album, String genre, String composer) {
		super();
		this.trackid = trackid;
		this.name = name;
		this.album = album;
		this.genre = genre;
		this.composer = composer;
	}

	public int getTrackid() {
		return trackid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	@Override
	public String toString() {
		return "trackid: " + trackid + ", name: " + name + ", album: " + album + ", genr: " + genre + ", composer: " + composer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(album, composer, genre, name, trackid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		return Objects.equals(album, other.album) && Objects.equals(composer, other.composer)
				&& Objects.equals(genre, other.genre) && Objects.equals(name, other.name) && trackid == other.trackid;
	}
	
		
	

}
