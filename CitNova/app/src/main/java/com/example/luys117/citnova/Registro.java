package com.example.luys117.citnova;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText nombre,correo,contraseña,motivo;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre=(EditText) findViewById(R.id.nombre);
        correo=(EditText) findViewById(R.id.correo);
        contraseña=(EditText) findViewById(R.id.contraseña);
        motivo=(EditText) findViewById(R.id.motivo);
        btn=(Button) findViewById(R.id.boton);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("lol", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent go=new Intent(getApplicationContext(),EleccionDeChat.class);
                    startActivity(go);
                } else {
                    // User is signed out
                    Log.d("lel", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom=nombre.getText().toString();
                String cor=correo.getText().toString();
                String con=contraseña.getText().toString();

                if (nom.length()==0 || cor.length()==0 || con.length()==0){
                    Toast.makeText(Registro.this,"Ingresa tus datos completos",Toast.LENGTH_SHORT).show();
                }
                else {
                    createAccount(cor,con);
                }
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAccount(String correo,String contra){
        mAuth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("lal", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Registro.this,"No existes viejo :/",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

}/*Fin de la clase*/
