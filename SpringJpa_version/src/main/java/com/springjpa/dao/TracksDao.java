package com.springjpa.dao;


import java.util.List;

import com.springjpa.model.Track;

public interface TracksDao {
	
	public void add(Track t);
	
	public Track get(int id);
	
	public Iterable<Track> findAll();
	
	public void update(Track t);
	
	public void delete(int id);
	
	public void delete(Track t);

	public List<Track> getByName(String name);
}
