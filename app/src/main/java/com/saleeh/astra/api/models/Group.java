package com.saleeh.astra.api.models;

import android.graphics.Color;

/**
 * Created by saleeh on 2/4/16.
 */
public class Group {
    public String id;
    public String name;
    public String color;
    public String point;

    public Group() {
    }

    public Group(String name, String color, String point) {
        this.name = name;
        this.color = color;
        this.point = point;
    }

    public int getColor() {
        int color = Color.parseColor(this.color);
        return color;
    }

}
