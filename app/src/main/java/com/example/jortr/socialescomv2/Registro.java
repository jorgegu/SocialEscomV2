package com.example.jortr.socialescomv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Registro extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registry : {
                Toast.makeText(getApplicationContext(), "Registrando", Toast.LENGTH_LONG).show();
                mRootRef = FirebaseDatabase.getInstance().getReference();
                mRootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild( ((EditText) findViewById(R.id.username)).getText().toString() )){
                            Toast.makeText(getApplicationContext(), "Usuario ya registrado", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Usuario nuevo", Toast.LENGTH_LONG).show();


                           mRootRef.updateChildren(new HashMap<String, Object>(){
                               {
                                   put(( (EditText) findViewById(R.id.username)).getText().toString() , new HashMap<String, Object>() {{
                                       put("pass", ( (EditText) findViewById(R.id.password)).getText().toString() );
                                       put("name", ( (EditText) findViewById(R.id.nombre)).getText().toString() );
                                       put("last", ( (EditText) findViewById(R.id.apellidos)).getText().toString() );
                                       put("nacimiento", ( (EditText) findViewById(R.id.fechaNac)).getText().toString() );
                                       put("sexo", ( (EditText) findViewById(R.id.sexo)).getText().toString() );
                                       put("tel", ( (EditText) findViewById(R.id.tel)).getText().toString() );
                                       put("escuela", ( (EditText) findViewById(R.id.escuela)).getText().toString() );
                                       put("ocupacion", ( (EditText) findViewById(R.id.ocupacion)).getText().toString() );
                                       if ( ( (RadioButton) findViewById(R.id.r1)).isChecked() ){
                                           put("tipo", "conductor");
                                       }else{
                                           put("tipo", "pasajero");
                                       }
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
        setContentView(R.layout.activity_registro);

        findViewById(R.id.registry).setOnClickListener(this);

    }
}
