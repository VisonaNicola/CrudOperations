package com.springjpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.Album;

public interface AlbumsRepository extends CrudRepository<Album,Integer>{

}
