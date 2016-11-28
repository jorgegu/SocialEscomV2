package com.example.jortr.socialescomv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        Button b_registry= (Button) findViewById(R.id.registry);
        final RadioButton rb_1=(RadioButton)findViewById(R.id.r1);
        final RadioButton rb_2=(RadioButton)findViewById(R.id.r2);


        b_registry.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                if (rb_1.isChecked()) {
                    final Intent h1 = new Intent(Registro.this, Automovil.class);
                    startActivity(h1);
                } else
                if (rb_2.isChecked()) {
                    Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();
                    final Intent h2 = new Intent(Registro.this, MainActivity.class);
                    startActivity(h2);
                }

            }

        });



    }
}
