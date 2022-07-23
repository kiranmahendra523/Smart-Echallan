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
import com.example.smartechallan.databinding.ActivityPredictTraffic2Binding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Predict_traffic_2 extends AppCompatActivity {
    ActivityPredictTraffic2Binding binding;
    ArrayAdapter<CharSequence> arrayAdapter;
    Spinner sp;
    String url = "https://traffic-prediction--app.herokuapp.com/predict";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPredictTraffic2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = binding.weatherSpinnerPre;
        arrayAdapter = ArrayAdapter.createFromResource(this,R.array.array_weather,R.layout.spinner_weather);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter);
        int pos = sp.getSelectedItemPosition()-1;
        String st = Integer.toString(pos);
        binding.buttonpredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dayValidate()|!tempValidate()|!timeValidate()){
                    return;
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data =jsonObject.getString("Traffic");
                            String result = setResultPre(data);
                            Toast.makeText(Predict_traffic_2.this, result, Toast.LENGTH_LONG).show();

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Predict_traffic_2.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        String timeSend = calTime(binding.timepre.getEditText().getText().toString());
                        params.put("CodedDay",binding.daypre.getEditText().getText().toString());
                        params.put("Zone",timeSend);
                        params.put("Weather",st);
                        params.put("Temperature",binding.temperaturepre.getEditText().getText().toString());
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(Predict_traffic_2.this);
                queue.add(stringRequest);
            }
        });
        binding.imagebackpredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Predict_traffic_2.this,UserMain.class));
            }
        });

    }
    private String setResultPre(String data){
        int x = Integer.parseInt(data);
        //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        if(x<=3){
            String m = "No Traffic";
            return m;
        }

        else{
            String m = "Heavy Traffic";
            return m;
        }
    }
    private String calTime(String s){
        String str = s.replaceAll("[^a-zA-Z0-9]","");
        int resl=0;
        int valu = Integer.parseInt(str);
        if(valu==0){
            resl=144;
        }
        else if(valu%100==0){
            resl = (valu/100)*6;
        }
        else{
            int rl =valu/100;
            rl=rl*6;
            valu=valu%100;
            int t =(int) valu/10;
            resl = rl + t;
        }
        String l = Integer.toString(resl);

        return l;
    }
    private boolean tempValidate(){
        String val = binding.temperaturepre.getEditText().getText().toString();
        if(val.isEmpty()){
            binding.temperaturepre.setError("this field cant be empty");
            return false;
        }
        else{
            binding.temperaturepre.setError(null);
            binding.temperaturepre.setErrorEnabled(false);
            return true;
        }
    }
    private boolean dayValidate(){
        String val = binding.daypre.getEditText().getText().toString();
        int k = Integer.parseInt(val);
        if(val.isEmpty()){
            binding.daypre.setError("this field cant be empty");
            return false;
        }
        
        else{
            binding.daypre.setError(null);
            binding.daypre.setErrorEnabled(false);
            return true;
        }
    }
    private  boolean timeValidate(){
        String val = binding.timepre.getEditText().getText().toString();

        if(val.isEmpty()){
            binding.timepre.setError("this field cant be empty");
            return false;
        }
        else{
            binding.timepre.setError(null);
            binding.timepre.setErrorEnabled(false);
            return true;
        }
    }
}