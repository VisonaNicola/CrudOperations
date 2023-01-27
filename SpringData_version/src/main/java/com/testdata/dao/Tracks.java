package com.testdata.dao;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

import com.testdata.bean.Track;

public class Tracks implements TracksDao{
	private JdbcTemplate conn;

	public Tracks(DataSource ds) {
		conn = new JdbcTemplate(ds);
	}

	/**
	 * Insert a new track in the db
	 */
	@Override
	public void insert(Track t) {
		String sql = "INSERT INTO tracks(Name,AlbumId,MediaTypeId,GenreId,Composer,Milliseconds,Bytes,UnitPrice) values(?,?,?,?,?,?,?,?)";
		conn.update(sql, t.getName(),t.getAlbumId(),t.getMediaTypeId(),t.getGenreId(),t.getComposer(),t.getMilliseconds(),t.getBytes(),t.getUnitPrice());		
	}

	/**
	 * Get all tracks from the db
	 */
	@Override
	public List<Track> getAll() {
		String sql = "SELECT * FROM tracks";
		return conn.query(sql,trackmapper);
	}
	/**
	 * Get a specific track from the db
	 */
	public Track get(int id) {
		String sql = "SELECT * FROM tracks WHERE TrackId="+id;
		return conn.queryForObject(sql,trackmapper);
	}
	
	/**
	 * Update a track from the db
	 */
	@Override
	public void update(Track t) {
		String sql = "UPDATE  tracks SET Name=?, AlbumId=?,MediaTypeId=?,GenreId=?,Composer=?,Milliseconds=?,Bytes=?,UnitPrice=? WHERE TrackId=?";
		conn.update(sql,t.getName(),t.getAlbumId(),t.getMediaTypeId(),t.getGenreId(),t.getComposer(),t.getMilliseconds(),t.getBytes(),t.getUnitPrice(),t.getTrackId());
	}

	/**
	 * Delete a track from the db
	 */
	@Override
	public void delete(Track t) {
		String sql = "DELETE FROM tracks WHERE TrackId=?";
		conn.update(sql,t.getTrackId());
	}
	
	
	
	private final RowMapper<Track> trackmapper = (resultSet,rowNum) ->{
		Track t = new Track();
		t.setTrackId(resultSet.getInt("TrackId"));
		t.setName(resultSet.getString("Name"));
		t.setAlbumId(resultSet.getInt("AlbumId"));
		t.setMediaTypeId(resultSet.getInt("MediaTypeId"));
		t.setGenreId(resultSet.getInt("GenreId"));
		t.setComposer(resultSet.getString("Composer"));
		t.setMilliseconds(resultSet.getInt("Milliseconds"));
		t.setBytes(resultSet.getInt("Bytes"));
		t.setUnitPrice(resultSet.getDouble("UnitPrice"));
		return t;
	};

	
}
