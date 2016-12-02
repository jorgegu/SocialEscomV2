package com.example.jortr.socialescomv2;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signin : {
                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                checkUser();
                break;
            }
            case R.id.signup : {
                startActivity(new Intent(MainActivity.this, Registro.class));
                break;
            }
        }
    }


    private void checkUser(){
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild( ((EditText) findViewById(R.id.username)).getText().toString() )){
                    DataSnapshot userSnap = dataSnapshot.child( ((EditText) findViewById(R.id.username)).getText().toString() );
                    if( userSnap.child( "pass" ).getValue().toString().equals( ((EditText) findViewById(R.id.password)).getText().toString() ) ){
                        Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("USER", ((EditText) findViewById(R.id.username)).getText().toString()).apply();

                        Log.d("sometag", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("USER", "defaultStringIfNothingFound") );

                        if(userSnap.child("tipo").getValue().toString().equals("conductor")){
                            startActivity(new Intent(MainActivity.this, Conductor.class));
                        }else{
                            startActivity(new Intent(MainActivity.this, Pasajero.class));
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password incorrecto pendejo de mierda", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Ocurrio un error en la base de datos", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // global stuff, not safe but is for fucking tomorrow
    DatabaseReference mRootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.signin)).setOnClickListener(this);

        (findViewById(R.id.signup)).setOnClickListener(this);

    }
}
