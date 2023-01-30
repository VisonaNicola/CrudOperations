package com.springjpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springjpa.model.Track;
import com.springjpa.repository.TracksRepository;

public class Tracks implements TracksDao{
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TracksRepository repo;
	
	@Override
	@Transactional
	public void add(Track t) {
		em.persist(t);
	}
	@Override
	public Track get(int id) {
		return em.find(Track.class, id);
		
	}
	@Override
	public Iterable<Track> findAll() {
		return repo.findAll();
	}
	@Override
	@Transactional
	public void update(Track t) {
		em.merge(t);
		
	}
	@Override
	@Transactional
	public void delete(int  id) {
		//em.remove(em.find(Track.class, id));
		repo.deleteById(id);
		
	}
	@Override
	@Transactional
	public void delete(Track t) {
//		if(em.contains(t))
//			em.remove(t);
		em.remove(em.merge(t));
		
	}
	
	@Override
	@Transactional
	public List<Track> getByName(String name) {
		return repo.findByName(name);
	}

}
