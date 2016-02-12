package com.saleeh.astra.api.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by saleeh on 2/12/16.
 */
@ParseClassName("user")
public class User extends ParseObject {
    public String name;
    public String dept;
    public String semester;
    public int color;

    public User() {
    }

    public void setUser(String name, String dept, String semester, int color) {
        this.name = name;
        this.dept = dept;
        this.semester = semester;
        this.color = color;
    }

}
