package com.example.catsimages;

import com.google.gson.annotations.SerializedName;

public class CatImage {

    @SerializedName("id")
    private final String id;

    @SerializedName("url")
    private final String url;

    @SerializedName("width")
    private final int width;

    @SerializedName("height")
    private final int height;

    public CatImage(String id, String url, int width, int height) {
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
