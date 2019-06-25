package com.example.networkmanager.domain;

import java.util.List;

public class JokeDtoWrapper {

    private List<JokeDto> value;

    public JokeDtoWrapper(List<JokeDto> value) {
        this.value = value;
    }

    public List<JokeDto> getValue() {
        return value;
    }
}
