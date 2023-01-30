package com.springjpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springjpa.model.Genre;
import com.springjpa.repository.GenresRepository;

public class Genres implements GenresDao{
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private GenresRepository repo;
	
	@Override
	public Genre get(int id) {
		return em.find(Genre.class, id);
	}

	@Override
	public Iterable<Genre> findAll() {
		return repo.findAll();
	}

}
