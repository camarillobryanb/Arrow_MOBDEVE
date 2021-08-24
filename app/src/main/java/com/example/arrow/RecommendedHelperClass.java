package com.example.arrow;

import android.view.View;
import android.widget.LinearLayout;

public class RecommendedHelperClass {
    int image;
    String name,description;

    public RecommendedHelperClass(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;

    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
