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

public class Pasajero extends AppCompatActivity {
    Request request;
    RequestQueue requestQueue;
    private static final String URL="https://rotted-buffer.000webhostapp.com/base/insert-pas.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasajero);
        requestQueue = Volley.newRequestQueue(this);
        Button addv= (Button) findViewById(R.id.add);
        final String id_viaje = ((EditText) findViewById(R.id.id_viaje)).getText().toString();

        addv.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){

                    public void onResponse(String response){
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("Yes")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("Yes"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Pasajero.this,MainActivity.class));
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
                        hashMap.put("id_viaje",id_viaje);

                        return hashMap;
                    }
                };
                requestQueue.add(request);

            }

        });
    }
}
