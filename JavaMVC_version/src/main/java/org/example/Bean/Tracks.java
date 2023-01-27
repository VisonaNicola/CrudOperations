package org.example.Bean;

import java.util.ArrayList;
import java.util.List;
/**
 * Bean for a collection of tracks.
 * @author nvisona
 *
 */
public class Tracks implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	List<Track> tracks;
	
	public Tracks() {
		super();
		tracks = new ArrayList<>();
	}

	public Tracks(List<Track> tracks) {
		super();
		this.tracks = tracks;
	}

	public List<Track> getTracks() {
		return tracks;
	}
	public void addTracks(List<Track> lista) {
		tracks=lista;
	}

	public void setTracks(List<Track> tracce) {
		this.tracks = tracce;
	}
	
	public void addTrack(Track t) {
		tracks.add(t);
	}
}
