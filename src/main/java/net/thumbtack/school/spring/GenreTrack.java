package net.thumbtack.school.spring;

public enum GenreTrack {
    ROCK("рок"),
    RAP("рэп"),
    POP("поп"),
    OTHER("другой");

    private String genreTrack;

    GenreTrack(String genreTrack) {
        this.genreTrack = genreTrack;
    }

    public String getGenreTrack() {
        return genreTrack;
    }
}
