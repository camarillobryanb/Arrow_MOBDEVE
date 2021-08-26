package com.example.arrow;

public class MyCardHelperClass {

    String name, description;
    int image, rating;

    public MyCardHelperClass(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public MyCardHelperClass(String name, String description, int image, int rating) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
