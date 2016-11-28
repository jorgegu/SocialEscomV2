package com.example.jortr.socialescomv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Automovil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automovil);


        Button b_registry= (Button) findViewById(R.id.registry);

        b_registry.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();
                final Intent h2 = new Intent(Automovil.this, MainActivity.class);
                startActivity(h2);

            }

        });


    }
}
