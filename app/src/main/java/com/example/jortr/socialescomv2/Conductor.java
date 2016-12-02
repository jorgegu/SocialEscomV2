package com.example.jortr.socialescomv2;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class Conductor extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addtravel : {
                mRootRef = FirebaseDatabase.getInstance().getReference();
                final DatabaseReference mTravels = mRootRef.child("viajes");
                mTravels.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("sometag", ((EditText) findViewById(R.id.viajeId)).getText().toString());
                        if(dataSnapshot.hasChild( ((EditText) findViewById(R.id.viajeId)).getText().toString())){

                            Toast.makeText(getApplicationContext(), "Id del viaje ya existe", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getApplicationContext(), "Agregando viaje", Toast.LENGTH_SHORT).show();

                            mTravels.updateChildren(new HashMap<String, Object>(){
                                {
                                    put(( (EditText) findViewById(R.id.viajeId)).getText().toString() , new HashMap<String, Object>() {{
                                        put("origen", ( (EditText) findViewById(R.id.origen)).getText().toString() );
                                        put("destino", ( (EditText) findViewById(R.id.destino)).getText().toString() );
                                        put("fecha", ( (EditText) findViewById(R.id.fecha)).getText().toString() );
                                        put("hora", ( (EditText) findViewById(R.id.hora)).getText().toString() );
                                        put("driver", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("USER", "defaultStringIfNothingFound"));
                                    }});
                                }});
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                break;
            }
        }
    }

    DatabaseReference mRootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);

        findViewById(R.id.addtravel).setOnClickListener(this);
    }
}
