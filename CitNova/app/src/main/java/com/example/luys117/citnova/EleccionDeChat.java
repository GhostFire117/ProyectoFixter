package com.example.luys117.citnova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EleccionDeChat extends AppCompatActivity {
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_de_chat);
        salir=(Button) findViewById(R.id.btnsalir);



        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exit=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(exit);
                finish();
            }
        });
    }
}
