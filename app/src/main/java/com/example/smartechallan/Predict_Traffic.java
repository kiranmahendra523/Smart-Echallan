package com.example.smartechallan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartechallan.databinding.ActivityPredictTrafficBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Predict_Traffic extends AppCompatActivity {
    Spinner wps;
    ActivityPredictTrafficBinding binding;
    ArrayAdapter<CharSequence> weatherAdp;
    String var = "https://traffic-predict-app.herokuapp.com/predict";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int timval,ti,pos;
        String timezone,weathercn;
        Toast.makeText(this, "bye", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        this.setTitle("Predict Traffic");
        binding = ActivityPredictTrafficBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "yy", Toast.LENGTH_SHORT).show();
       // wps = binding.weatherSpinner;
        weatherAdp = ArrayAdapter.createFromResource(this,R.array.array_weather,R.layout.spinner_weather);
        weatherAdp.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        wps.setAdapter(weatherAdp);
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
       // String s = binding.time.getEditText().getText().toString();
        //String str = s.replaceAll("[^0-9]","");
       // timval = Integer.parseInt(str);

        /*if(timval==0){
            ti=144;
        }
        else if(timval%100==0){
            ti = (timval/100)*6;
        }
        else{
            int r = timval/100;
            r=r*6;
            timval=timval%100;
            int t = timval/10;
            ti =r+t;
        }*/
        /*Toast.makeText(this, "Hii", Toast.LENGTH_SHORT).show();
        pos = wps.getSelectedItemPosition()-1;
        weathercn = Integer.toString(pos);
        timezone = Integer.toString(ti);*/
        /*binding.image345.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Predict_Traffic.this,UserMain.class));

            }
        })*/
        /*binding.buttonpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, var, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("");
                            int k = Integer.parseInt(data);
                            if(k<3){
                                binding.res.setText("No Traffic");
                            }
                            else if(k>3){
                                binding.res.setText("High traffic");
                            }
                            else{
                                binding.res.setText("Mild Traffic");
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Predict_Traffic.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> parms = new HashMap<String,String>();
                        parms.put("CodedDay",binding.day.getEditText().getText().toString());
                        parms.put("Zone",timezone);
                        parms.put("Weather",weathercn);
                        parms.put("Temperature",binding.temperature.getEditText().getText().toString() );
                        return parms;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(Predict_Traffic.this);
                queue.add(stringRequest);
            }
        });*/

    }
}