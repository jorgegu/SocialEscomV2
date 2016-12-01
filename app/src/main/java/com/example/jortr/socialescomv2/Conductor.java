package com.example.jortr.socialescomv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Conductor extends AppCompatActivity {

    Request request;
    RequestQueue requestQueue;
    private static final String URL="https://rotted-buffer.000webhostapp.com/base/insert-viaje.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);
        requestQueue = Volley.newRequestQueue(this);
        Button b_add= (Button) findViewById(R.id.add);
        final String origen = ((EditText) findViewById(R.id.origen)).getText().toString();
        final String destino = ((EditText) findViewById(R.id.destino)).getText().toString();
        final String fecha = ((EditText) findViewById(R.id.fecha)).getText().toString();
        final String hora = ((EditText) findViewById(R.id.hora)).getText().toString();


        b_add.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){

                    public void onResponse(String response){
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("Yes")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("Yes"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Conductor.this,MainActivity.class));
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
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("origen",origen);
                        hashMap.put("destino",destino);
                        hashMap.put("fecha",fecha);
                        hashMap.put("hora",hora);

                        return hashMap;
                    }
                };
                requestQueue.add(request);


               /* Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();
                final Intent h2 = new Intent(Automovil.this, MainActivity.class);
                startActivity(h2);*/

            }

        });
    }
}
