package com.example.springboot.repository;

import com.example.springboot.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TracksRepository extends JpaRepository<Track,Integer> {
}
