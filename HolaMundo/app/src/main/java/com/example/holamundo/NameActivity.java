package com.example.holamundo;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NameActivity extends AppCompatActivity {

    TextView tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        tvNombre = findViewById(R.id.tv_mombre);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String nombre = bundle.getString("nombre");
            tvNombre.setText(nombre);
        }
    }
}