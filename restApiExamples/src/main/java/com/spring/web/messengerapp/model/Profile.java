package com.spring.web.messengerapp.model;

public class Profile {
    private Long id;
    private String profileName;
    private String name;
    private String surname;

    public Profile(Long id, String profileName, String name, String surname) {
        this.id = id;
        this.profileName = profileName;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
