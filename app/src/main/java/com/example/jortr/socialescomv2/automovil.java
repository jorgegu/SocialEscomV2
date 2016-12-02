package com.example.jortr.socialescomv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class automovil extends AppCompatActivity {

    Request request;
    RequestQueue requestQueue;
    private static final String URL="https://rotted-buffer.000webhostapp.com/base/insert-auto.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automovil);
        requestQueue = Volley.newRequestQueue(this);
        Button b_registry= (Button) findViewById(R.id.registry);
        final String marca = ((EditText) findViewById(R.id.marca)).getText().toString();
        final String modelo = ((EditText) findViewById(R.id.modelo)).getText().toString();
        final String color = ((EditText) findViewById(R.id.color)).getText().toString();
        final String asientos = ((EditText) findViewById(R.id.asientos)).getText().toString();
        final String placas = ((EditText) findViewById(R.id.placas)).getText().toString();




        b_registry.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){

                    public void onResponse(String response){
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("Yes")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("Yes"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(automovil.this,MainActivity.class));
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
                        hashMap.put("marca",marca);
                        hashMap.put("modelo",modelo);
                        hashMap.put("color",color);
                        hashMap.put("asientos",asientos);
                        hashMap.put("placas",placas);

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
