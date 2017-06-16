package com.example.luys117.citnova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    EditText email,contraseña;
    ImageView registro, sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText) findViewById(R.id.correo);
        contraseña=(EditText) findViewById(R.id.contraseña);
        registro=(ImageView) findViewById(R.id.Btnregistro);
        sesion=(ImageView) findViewById(R.id.Btnsesion);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg=new Intent(getApplicationContext(),Registro.class);
                startActivity(reg);
            }
        });
    
        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isesion=new Intent(getApplicationContext(),EleccionDeChat.class);
                startActivity(isesion);
            }
        });


    }
}
