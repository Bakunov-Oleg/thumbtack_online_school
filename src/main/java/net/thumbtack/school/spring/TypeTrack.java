package net.thumbtack.school.spring;

public enum TypeTrack {

    AUDIO("audio"),
    VIDEO("video");

    private String typeTrack;

    TypeTrack(String typeTrack) {
        this.typeTrack = typeTrack;
    }

    public String getTypeTrack() {
        return typeTrack;
    }

}
