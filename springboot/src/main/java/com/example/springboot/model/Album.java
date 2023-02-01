package com.example.springboot.model;

import javax.persistence.*;

@Embeddable
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="AlbumId")
    private int albumId;

    @Column(name="Title")
    private String title;

    @Column(name="ArtistId")
    private int artistId;


    public Album() {
        super();
    }

    public Album(int albumId, String title, int artistId) {
        super();
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
    }


    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
