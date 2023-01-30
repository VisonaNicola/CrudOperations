package com.springjpa.dao;

import com.springjpa.model.Album;

public interface AlbumsDao {	
	public Album get(int id);
	
	public Iterable<Album> findAll();
}
