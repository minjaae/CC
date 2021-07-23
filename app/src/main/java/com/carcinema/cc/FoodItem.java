package com.carcinema.cc;

import android.graphics.drawable.Drawable;

public class FoodItem {

    private Drawable icon;
    private Drawable icon2;
    private Drawable foodicon;
    private String name;
    private String contents;

    public Drawable getFoodIcon() {
        return foodicon;
    }
    public void setFoodIcon(Drawable icon) {
        this.foodicon = icon;
    }
    public Drawable getIcon() {
        return icon;
    }
    public Drawable getIcon2() {
        return icon2;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setIcon2(Drawable icon2) {
        this.icon2 = icon2;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
