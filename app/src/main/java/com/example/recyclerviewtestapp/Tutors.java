package com.example.recyclerviewtestapp;

public class Tutors {
    private String name, about, email, module;

    public Tutors()
    {}

    public Tutors(String name, String about, String email, String module)
    {

        this.about = about;
        this.email = email;
        this.module = module;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
