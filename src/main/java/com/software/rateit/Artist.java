package com.software.rateit;

public class Artist {
    private int id;
    private String stageName;
    //@ManyToMany
    private CD cd;
    //@ManyToMany
    //private Track track;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }
}
