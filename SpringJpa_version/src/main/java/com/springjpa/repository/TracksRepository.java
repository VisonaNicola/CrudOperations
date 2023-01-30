package com.springjpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.Track;

public interface TracksRepository extends CrudRepository<Track,Integer>{
	public List<Track> findByName(String name);	//la firma non viene implementata, ci pensa spring a farlo
}
