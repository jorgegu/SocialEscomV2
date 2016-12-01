package com.example.jortr.socialescomv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class Registro extends AppCompatActivity {

    Request request;
    RequestQueue requestQueue;
    private static final String URL="https://rotted-buffer.000webhostapp.com/base/insert.php";
    String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        requestQueue = Volley.newRequestQueue(this);
        Button b_registry= (Button) findViewById(R.id.registry);
        final RadioButton rb_1=(RadioButton)findViewById(R.id.r1);
        final RadioButton rb_2=(RadioButton)findViewById(R.id.r2);


        final String email = ((EditText) findViewById(R.id.email)).getText().toString();
        final String password = ((EditText) findViewById(R.id.password)).getText().toString();
        final String nombre = ((EditText) findViewById(R.id.nombre)).getText().toString();
        final String apellidos = ((EditText) findViewById(R.id.apellidos)).getText().toString();
        final String fechaNac = ((EditText) findViewById(R.id.fechaNac)).getText().toString();
        final String sexo = ((EditText) findViewById(R.id.sexo)).getText().toString();
        final String tel = ((EditText) findViewById(R.id.tel)).getText().toString();
        final String escuela = ((EditText) findViewById(R.id.escuela)).getText().toString();
        final String ocupacion = ((EditText) findViewById(R.id.ocupacion)).getText().toString();
        b_registry.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                if (rb_1.isChecked()) {
                    tipo ="conductor";
                    final Intent h1 = new Intent(Registro.this, Automovil.class);
                    startActivity(h1);
                } else
                if (rb_2.isChecked()) {
                    tipo="pasajero";
                    Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();
                    final Intent h2 = new Intent(Registro.this, MainActivity.class);
                    startActivity(h2);
                }

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){

                    public void onResponse(String response){
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("Yes")){
                                Toast.makeText(getApplicationContext(),jsonObject.getString("Yes"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registro.this,MainActivity.class));
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
                        hashMap.put("email",email);
                        hashMap.put("password",password);
                        hashMap.put("nombre",nombre);
                        hashMap.put("apellidos",apellidos);
                        hashMap.put("fechaNac",fechaNac);
                        hashMap.put("sexo",sexo);
                        hashMap.put("tel",tel);
                        hashMap.put("escuela",escuela);
                        hashMap.put("ocupacion",ocupacion);
                        hashMap.put("tipo",tipo);


                        return hashMap;
                    }
                };
                requestQueue.add(request);

            }

        });



    }
}
