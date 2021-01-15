package net.thumbtack.school.spring.models;

import net.thumbtack.school.spring.GenreTrack;
import net.thumbtack.school.spring.TypeTrack;

import java.net.URL;

public class Recording {
    private String artist;
    private TypeTrack typeTrack;
    private String name;
    private String nameAlbum;
    private int year;
    private URL coverUrl;
    private GenreTrack genre;
    private double timeLen;
    private URL contentUrl;

    public Recording(String artist, TypeTrack typeTrack, String name, String nameAlbum, int year, URL coverUrl, GenreTrack genre, double timeLen, URL contentUrl) {
        this.artist = artist;
        this.typeTrack = typeTrack;
        this.name = name;
        this.nameAlbum = nameAlbum;
        this.year = year;
        this.coverUrl = coverUrl;
        this.genre = genre;
        this.timeLen = timeLen;
        this.contentUrl = contentUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public TypeTrack getTypeTrack() {
        return typeTrack;
    }

    public void setTypeTrack(TypeTrack typeTrack) {
        this.typeTrack = typeTrack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public URL getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(URL coverUrl) {
        this.coverUrl = coverUrl;
    }

    public GenreTrack getGenre() {
        return genre;
    }

    public void setGenre(GenreTrack genre) {
        this.genre = genre;
    }

    public double getTimeLen() {
        return timeLen;
    }

    public void setTimeLen(double timeLen) {
        this.timeLen = timeLen;
    }

    public URL getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(URL contentUrl) {
        this.contentUrl = contentUrl;
    }
}
