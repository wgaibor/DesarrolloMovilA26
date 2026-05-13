package com.example.holamundo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtNombre;
    Button btnEnviar;
    LinearLayout principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNombre = findViewById(R.id.edt_nombre);
        btnEnviar = findViewById(R.id.btn_enviar);
        principal = findViewById(R.id.main);
        btnEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_enviar ) {
            //Toast.makeText(this, "Tu nombre es  "+ edtNombre.getText().toString() , Toast.LENGTH_LONG).show();
            //Snackbar.make(principal, "Tu nombre es  "+ edtNombre.getText().toString(), Snackbar.LENGTH_LONG).show();
            mostrarMensaje("Tu nombre es  "+ edtNombre.getText().toString());
        }
    }

    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Mensaje app");
        builder.setMessage(mensaje);
        builder.setCancelable(false);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Has seleccionado Aceptar  " , Toast.LENGTH_LONG).show();
                Intent intento = new Intent(MainActivity.this, NameActivity.class);
                intento.putExtra("nombre", edtNombre.getText().toString());
                startActivity(intento);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Has seleccionado Cancelar  " , Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}