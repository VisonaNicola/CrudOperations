package com.springjpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springjpa.model.Album;
import com.springjpa.repository.AlbumsRepository;

public class Albums implements AlbumsDao{
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private AlbumsRepository repo;

	@Override
	public Album get(int id) {
		return em.find(Album.class, id);
	}

	@Override
	public Iterable<Album> findAll() {
		return repo.findAll();
	}
}
