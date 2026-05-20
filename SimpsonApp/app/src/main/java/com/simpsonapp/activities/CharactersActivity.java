package com.simpsonapp.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.simpsonapp.R;
import com.simpsonapp.models.Characters;
import com.simpsonapp.models.SimpsonResponse;
import com.simpsonapp.services.SimpsonApiServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersActivity extends AppCompatActivity {

    SimpsonApiServices apiServices;

    RecyclerView rvCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        rvCharacters = findViewById(R.id.rv_character);
        cargarInformacion();
    }

    private void cargarInformacion() {
        apiServices = SimpsonApiServices.getInstance();
        apiServices.getApi().getPersonajeSimpson().enqueue(new Callback<SimpsonResponse>() {
            @Override
            public void onResponse(Call<SimpsonResponse> call, Response<SimpsonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mostrarMensaje(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<SimpsonResponse> call, Throwable t) {

            }
        });
    }

    private void mostrarMensaje(List<Characters> lstCharacters) {
        for (Characters personaje:  lstCharacters) {
            Log.d("Personaje ", personaje.getName());
        }
    }
}