package com.saleeh.astra.api.models;

import android.graphics.Color;

/**
 * Created by saleeh on 2/4/16.
 */
public class Participant {
    public String id;
    public String name;
    public String dept;
    public String sem;
    public String color;

    public String position = "1";

    public String point = "1";

    public Participant() {
    }


    public Participant(String name, String dept, String sem, String color) {
        this.name = name;
        this.dept = dept;
        this.sem = sem;
        this.color = color;
    }

    public Participant(String name, String dept, String sem, String color, String position) {
        this.name = name;
        this.dept = dept;
        this.sem = sem;
        this.color = color;
        this.position = position;
    }

    public int getColor() {
        int color = Color.parseColor(this.color);
        return color;
    }

    public int getTextColor() {
        int color = Color.parseColor(this.color);
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        if (darkness < 0.5) {
            return Color.BLACK; // It's a light color
        } else {
            return Color.WHITE; // It's a dark color
        }
    }
}
