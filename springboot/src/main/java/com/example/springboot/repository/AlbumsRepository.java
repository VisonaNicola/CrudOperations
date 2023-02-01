package com.example.springboot.repository;

import com.example.springboot.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumsRepository extends JpaRepository<Album,Integer> {
}
