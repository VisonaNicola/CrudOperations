package com.springjpa.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity
@Table(name = "tracks")
@SecondaryTables({
	@SecondaryTable(name = "genres", pkJoinColumns = @PrimaryKeyJoinColumn(name = "GenreId")),
	@SecondaryTable(name = "albums", pkJoinColumns = @PrimaryKeyJoinColumn(name = "AlbumId"))
})


public class Track {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TrackId")
	private int trackId;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="MediaTypeId")
	private int mediaTypeId;
	
	@Column(name="Composer")
	private String composer;
	
	@Column(name="Milliseconds")
	private int milliseconds;
	
	@Column(name="Bytes")
	private int bytes;
	
	@Column(name="UnitPrice")
	private double unitPrice;
	
	@Embedded
	@ManyToOne
    @JoinColumn(name = "GenreId", nullable = true)
	private Genre genre;
	
	@Embedded
	@ManyToOne
    @JoinColumn(name = "AlbumId", nullable = true)
	private Album album;	

	public Track() {
		super();
	}
	
	public Track(String name, Album album, int mediaTypeId, Genre genre, String composer, int milliseconds,	int bytes, double unitPrice) {
		super();
		this.name = name;
		this.album = album;
		this.mediaTypeId = mediaTypeId;
		this.composer = composer;
		this.milliseconds = milliseconds;
		this.bytes = bytes;
		this.unitPrice = unitPrice;
		this.genre = genre;
	}

	public Track(int trackId, String name, Album album, int mediaTypeId, String composer, int milliseconds, int bytes, double unitPrice, Genre genre) {
		super();
		this.trackId = trackId;
		this.name = name;
		this.album = album;
		this.mediaTypeId = mediaTypeId;
		this.composer = composer;
		this.milliseconds = milliseconds;
		this.bytes = bytes;
		this.unitPrice = unitPrice;
		this.genre = genre;
	}

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public int getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(int mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", name=" + name + ", album Title=" + album.getTitle() + ", mediaTypeId=" + mediaTypeId
				+ ", genre=" + genre.getName() + ", composer=" + composer + ", milliseconds=" + milliseconds + ", bytes="
				+ bytes + ", unitPrice=" + unitPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(trackId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		return trackId == other.trackId;
	}	
}
