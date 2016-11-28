package com.example.jortr.socialescomv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b_signin= (Button) findViewById(R.id.signin);
        Button b_signup= (Button) findViewById(R.id.signup);


        b_signin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();


                if (username.equals("user1") && password.equals("user1"))
                {
                    final Intent h1 = new Intent(MainActivity.this, Conductor.class);
                    //h1.putExtra("usuario","george");
                    startActivity(h1);
                }
                else if(username.equals("user2") && password.equals("user2"))
                {

                    final Intent h1 = new Intent(MainActivity.this, Pasajero.class);
                    //h1.putExtra("usuario","george");
                    startActivity(h1);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

        });

        b_signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final Intent h2 = new Intent(MainActivity.this, Registro.class);
                startActivity(h2);
            }
        });



    }
}
