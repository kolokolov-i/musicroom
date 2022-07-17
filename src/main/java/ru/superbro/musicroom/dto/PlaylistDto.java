package ru.superbro.musicroom.dto;

public class PlaylistDto {

    private String name;
    private long photoId;

    public PlaylistDto(String name, long photoId) {
        this.name = name;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

}
