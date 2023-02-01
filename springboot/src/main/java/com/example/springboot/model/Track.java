package com.example.springboot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="TrackId")
    private int trackId;
    @Column(name="Name")
    private String name;

//    @Column(name="AlbumId")
//    private int albumId;
    @Column(name="MediaTypeId")
    private int mediaTypeId;

//    @Column(name="GenreId")
//    private int genreId;
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

    public Track(String name, /*int albumId*/Album album, int mediaTypeId,/* int genreId*/ Genre genre, String composer, int milliseconds,	int bytes, double unitPrice) {
        super();
        this.name = name;
        //this.albumId = albumId;
        this.mediaTypeId = mediaTypeId;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
        //this.genreId = genreId;
        this.album=album;
        this.genre=genre;
    }

    public Track(int trackId,String name, /*int albumId*/Album album, int mediaTypeId,/* int genreId*/ Genre genre, String composer, int milliseconds,	int bytes, double unitPrice) {
        super();
        this.trackId=trackId;
        this.name = name;
        //this.albumId = albumId;
        this.mediaTypeId = mediaTypeId;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
        //this.genreId = genreId;
        this.album=album;
        this.genre=genre;
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

//    public int getAlbumId() {
//        return albumId;
//    }
//
//    public void setAlbumId(int albumId) {
//        this.albumId = albumId;
//    }
//
//    public int getGenreId() {
//        return genreId;
//    }
//
//    public void setGenreId(int genreId) {
//        this.genreId = genreId;
//    }


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", name='" + name + '\'' +
                ", mediaTypeId=" + mediaTypeId +
                ", composer='" + composer + '\'' +
                ", milliseconds=" + milliseconds +
                ", bytes=" + bytes +
                ", unitPrice=" + unitPrice +
                ", album=" + album.getTitle() +
                ", genre=" + genre.getName() +
                '}';
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
