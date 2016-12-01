package com.example.jortr.socialescomv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Application;
import android.database.Cursor;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class MainActivity extends AppCompatActivity {


    Request request;
    RequestQueue requestQueue;
    private static final String URL="https://rotted-buffer.000webhostapp.com/base/user_control.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        final String username = ((EditText) findViewById(R.id.username)).getText().toString();
        final String password = ((EditText) findViewById(R.id.password)).getText().toString();
        Button b_signin= (Button) findViewById(R.id.signin);
        Button b_signup= (Button) findViewById(R.id.signup);

        b_signin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){


                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){

                    public void onResponse(String response){
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("Yes")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("Yes"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,Conductor.class));
                            }else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("Error"), Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        //
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("email",username);
                        hashMap.put("password",password);
                        return hashMap;
                    }
                };
                requestQueue.add(request);
/*
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

                */
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
