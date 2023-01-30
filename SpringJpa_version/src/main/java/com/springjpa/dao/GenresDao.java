package com.springjpa.dao;


import com.springjpa.model.Genre;

public interface GenresDao {
	
	public Genre get(int id);
	
	public Iterable<Genre> findAll();
}
