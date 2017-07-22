package com.example.pol.testapplication;

/**
 * Created by pol on 7/22/17.
 */
public class Oompa {

    private String name;
    private String lastName;
    private int id;
    private String email;
    private Character gender;
    private String profession;
    private String thumbnail;
    private String imageUrl;

    //this should be done with proper constructors
    public Oompa(String name, String lastName, int id, String email, Character gender, String profession, String thumbnail, String imageUrl) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.profession = profession;
        this.thumbnail = thumbnail;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
