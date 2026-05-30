package com.simpsonapp.models;

public class Characters {
    private String name;
    private String portrait_path;
    private int age;
    private String occupation;
    private String status;
    private String[] phrases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait_path() {
        return portrait_path;
    }

    public void setPortrait_path(String portrait_path) {
        this.portrait_path = portrait_path;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getPhrases() {
        return phrases;
    }

    public void setPhrases(String[] phrases) {
        this.phrases = phrases;
    }
}
