package com.simpsonapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simpsonapp.R;
import com.simpsonapp.adapter.CharacterAdpater;
import com.simpsonapp.models.Characters;
import com.simpsonapp.models.SimpsonResponse;
import com.simpsonapp.services.SimpsonApiServices;
import com.simpsonapp.util.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersActivity extends AppCompatActivity {

    SimpsonApiServices apiServices;

    RecyclerView rvCharacters;

    CharacterAdpater characterAdpater;

    GridLayoutManager gridLayoutManager;

    int pageRecently = 1;

    boolean isLoading = false;

    List<Characters> lstCharacter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        rvCharacters = findViewById(R.id.rv_character);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        gridLayoutManager = new GridLayoutManager(this, 2);
        lstCharacter = new ArrayList<>();
        fillRecyclerView(lstCharacter);
        settingScrollListener();
        callCharacterSimpsons(pageRecently);
    }

    private void settingScrollListener() {
        rvCharacters.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItems = gridLayoutManager.getItemCount();
                int lastItemVisible = gridLayoutManager.findLastVisibleItemPosition();
                // Si estamos cerca del final del recyclerview ('ultimos 5 elementos) y no este consumiendo ws
                if (!isLoading && lastItemVisible >= totalItems - 5) {
                    loadNextItems();
                    Log.i("Pagina actual", pageRecently+"");
                }
            }
        });
    }

    private void loadNextItems() {
        if (!isLoading) {
            pageRecently++;
            callCharacterSimpsons(pageRecently);
        }
    }

    private void callCharacterSimpsons(int pageRecently) {
        apiServices = SimpsonApiServices.getInstance();
        isLoading = true;
        apiServices.getApi().getPersonajeSimpsonByPage(pageRecently).enqueue(new Callback<SimpsonResponse>() {
            @Override
            public void onResponse(Call<SimpsonResponse> call, Response<SimpsonResponse> response) {
                isLoading = false;
                if (response.isSuccessful() && response.body() != null) {
                    List<Characters> lstCharacter = response.body().getResults();
                    if (pageRecently == 1) {
                        fillRecyclerView(lstCharacter);
                    } else {
                        characterAdpater.addCharacterNew(lstCharacter);
                    }
                }
            }

            @Override
            public void onFailure(Call<SimpsonResponse> call, Throwable t) {
                isLoading = false;
                t.printStackTrace();
            }
        });
    }


    private void fillRecyclerView(List<Characters> lstCharacters) {
        characterAdpater = new CharacterAdpater(lstCharacters, this);
        rvCharacters.setHasFixedSize(true);
        rvCharacters.setLayoutManager(gridLayoutManager);
        rvCharacters.setAdapter(characterAdpater);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logoutSession();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutSession() {
        SharedPreferencesManager.setValorBoolean(this, "SimpsonPreferences", false, "isLogin");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}