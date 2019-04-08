package com.software.rateit;
public class CD {
    private int id;
    private String name;
    private String released;
    //@ManyToMany
    //private Artist artist;
    //@ManyToMany
    //private Track track;
    //@ManyToMany
    //private Type type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }
}
