package com.springjpa.repository;


import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.Genre;

public interface GenresRepository extends CrudRepository<Genre,Integer>{
}
