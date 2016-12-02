package com.example.jortr.socialescomv2;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Pasajero extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addv: {
                final DatabaseReference mTravel = mRootRef.child("viajes");
                mTravel.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(((EditText) findViewById(R.id.id_viaje)).getText().toString())) {
                            DatabaseReference mUniqueTravel = mTravel.child( ((EditText) findViewById(R.id.id_viaje)).getText().toString() );

                            final DatabaseReference mPasajeros = mUniqueTravel.child("pasajeros");
                            
                            mPasajeros.updateChildren(new HashMap<String, Object>(){
                                {
                                    put( PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("USER", "defaultStringIfNothingFound"), "" );
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
        setContentView(R.layout.activity_pasajero);

        findViewById(R.id.addv).setOnClickListener(this);
        mRootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference mTravel = mRootRef.child("viajes");
        mTravel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ((TextView) findViewById(R.id.listTravel)).setText(dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
