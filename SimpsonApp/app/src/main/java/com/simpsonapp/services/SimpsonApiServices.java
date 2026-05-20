package com.simpsonapp.services;

import com.simpsonapp.models.SimpsonResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class SimpsonApiServices {

    private static final String BASE_URL = "https://thesimpsonsapi.com/api/";

    private static SimpsonApiServices instance;

    private SimponsApi api;

    public SimpsonApiServices() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(SimponsApi.class);
    }

    public static SimpsonApiServices getInstance() {
        if (instance == null) {
            instance = new SimpsonApiServices();
        }
        return instance;
    }

    public SimponsApi getApi() {
        return api;
    }

    public interface SimponsApi {

        @GET("characters")
        Call<SimpsonResponse> getPersonajeSimpson();
    }
}
