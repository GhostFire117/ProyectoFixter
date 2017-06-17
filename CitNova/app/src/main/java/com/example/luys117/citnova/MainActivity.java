package com.example.luys117.citnova;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email,contraseña;
    ImageView registro, sesion;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText) findViewById(R.id.correo);
        contraseña=(EditText) findViewById(R.id.contraseña);
        registro=(ImageView) findViewById(R.id.Btnregistro);
        sesion=(ImageView) findViewById(R.id.Btnsesion);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("lol", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent sigue=new Intent(getApplicationContext(),Chat.class);
                    startActivity(sigue);
                } else {
                    // User is signed out
                    Log.d("lel", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

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
                String a=email.getText().toString();
                String b=contraseña.getText().toString();
                if (a.length()==0 || b.length()==0){
                    Toast.makeText(MainActivity.this,"Ingresa tus datos completos por favor",Toast.LENGTH_SHORT).show();
                }
                else {
                    singIn(a, b);
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

    private void singIn(String email, String contraseña){
        mAuth.signInWithEmailAndPassword(email, contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("lol", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("lol", "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this,"No estás registrado", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}
