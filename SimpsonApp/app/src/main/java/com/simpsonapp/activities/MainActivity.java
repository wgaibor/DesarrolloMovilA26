package com.simpsonapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.simpsonapp.R;
import com.simpsonapp.util.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText txtUser;
    TextInputEditText txtPass;
    TextInputLayout tilUser;
    TextInputLayout tilPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = findViewById(R.id.tie_user);
        txtPass = findViewById(R.id.tie_pass);
        btnLogin = findViewById(R.id.btn_enter);
        tilUser = findViewById(R.id.til_user);
        tilPass = findViewById(R.id.til_pass);
        btnLogin.setOnClickListener(this);
        boolean isLoginNow = SharedPreferencesManager.getValorEsperadoBoolean(this, "SimpsonPreferences", "isLogin");
        if (isLoginNow) {
            callNextActivity();
        }
        txtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tilUser.getError() != null) {
                    tilUser.setError(null);
                }
            }
        });

        txtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tilPass.getError() != null) {
                    tilPass.setError(null);
                }
            }
        });
    }

    private void callNextActivity() {
        Intent intento = new Intent(this, CharactersActivity.class);
        startActivity(intento);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_enter) {
            String user = txtUser.getText().toString();
            String pass = txtPass.getText().toString();
            if (user.isEmpty()) {
                tilUser.setError("El campo usuario es obligatorio");
                return;
            }
            if (pass.isEmpty()) {
                tilPass.setError("El campo contrasena es obligatorio");
                return;
            }
            validateUser(user, pass);
        }
    }

    private void validateUser(String user, String pass) {
        if (user.equalsIgnoreCase("wgaibor") && pass.equalsIgnoreCase("123")) {
            SharedPreferencesManager.setValorBoolean(this, "SimpsonPreferences", true, "isLogin");
            callNextActivity();
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
        clearTextField();
    }

    private void clearTextField() {
        txtUser.setText("");
        txtPass.setText("");
    }
}