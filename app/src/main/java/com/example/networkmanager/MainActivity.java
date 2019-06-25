package com.example.networkmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.networkmanager.Service.IcndbRetrofitApi;
import com.example.networkmanager.domain.JokeDto;
import com.example.networkmanager.domain.JokeDtoWrapper;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivSample;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivSample = findViewById(R.id.activity_main__img__sample);

        final Button btnData = findViewById(R.id.activity_main__btn__get_data);
        btnData.setOnClickListener(this);

        final Button btnImage = findViewById(R.id.activity_main__btn__fetch_image);
        btnImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Connectivity Manager answers queries about the state of the network
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Network Info describes the status of a network interface
        NetworkInfo info = cm.getActiveNetworkInfo();

        //isConnected() handles cases like flaky mobile networks, airplane mode , etc
        if (info != null && info.isConnected()) {

            switch (v.getId()) {
                case R.id.activity_main__btn__fetch_image:

                    Glide.with(this)
                            .load("http://goo.gl/gEgYUd")
                            .into(ivSample);

                    break;
                case R.id.activity_main__btn__get_data:

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.icndb.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    Call<JokeDtoWrapper> jokeListQuery =
                            retrofit.create(IcndbRetrofitApi.class).getJokeList();

                    Call<JokeDto> randomJokeQuery =
                            retrofit.create(IcndbRetrofitApi.class).getRandomJoke();

                    jokeListQuery.enqueue(new Callback<JokeDtoWrapper>() {
                        @Override
                        public void onResponse(Call<JokeDtoWrapper> call, Response<JokeDtoWrapper> response) {

                            if (response.isSuccessful()) {
                                List<JokeDto> jokeList = response.body().getValue();
                                //Toast.makeText(MainActivity.this,"size: " + jokeList.size(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this,
                                        jokeList.get(getRandomNumber(jokeList.size())).toString(),
                                        Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<JokeDtoWrapper> call, Throwable t) {

                        }
                    });

                    break;
                default:
                    Log.w("", "clickable view not controlled");
            }
        } else {
            Toast.makeText(this, "Connection  not available", Toast.LENGTH_SHORT).show();
        }

    }

    private int getRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound);
    }


}
