package com.testdata.dao;

import java.util.List;

import com.testdata.bean.Track;

public interface TracksDao {
	
	public void insert(Track t);

	public List<Track> getAll();
	
	public Track get(int id);
	
	public void update(Track t);
	
	public void delete(Track t);
}
