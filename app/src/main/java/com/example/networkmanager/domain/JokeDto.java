package com.example.networkmanager.domain;

import androidx.annotation.NonNull;

import java.util.List;

public class JokeDto {

    private int id;
    private String joke;
    private List<String> categories;

    public JokeDto(int id, String joke, List<String> categories) {
        this.id = id;
        this.joke = joke;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    public List<String> getCategories() {
        return categories;
    }

    @NonNull
    @Override
    public String toString() {
        return "Joke #" + this.id + ":\n\n" + this.joke;
    }
}
