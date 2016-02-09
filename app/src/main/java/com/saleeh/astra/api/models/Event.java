package com.saleeh.astra.api.models;

/**
 * Created by saleeh on 2/4/16.
 */
public class Event {
    public String id;
    public String name;
    public String image;
    public String point;

    public Event() {
    }

    public Event(String name, String image, String point) {
        this.name = name;
        this.image = image;
        this.point = point;
    }


}
