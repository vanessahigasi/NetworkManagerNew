package com.example.networkmanager.Service;

import com.example.networkmanager.domain.JokeDto;
import com.example.networkmanager.domain.JokeDtoWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IcndbRetrofitApi {

    @GET("jokes")
    Call<JokeDtoWrapper> getJokeList();

    @GET("jokes/random")
    Call<JokeDto> getRandomJoke();
}
