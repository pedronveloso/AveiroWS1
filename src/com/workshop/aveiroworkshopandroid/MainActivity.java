package com.workshop.aveiroworkshopandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView tvAux = (TextView) findViewById(R.id.tv_current_temp);

        tvAux.setText("20º");

        tvAux = (TextView) findViewById(R.id.tv_max_temp);

        tvAux.setText("24º");


        tvAux = (TextView) findViewById(R.id.tv_min_temp);

        tvAux.setText("18º");

        new DownloadTask().execute();
    }



    private class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params){
            return NetworkCalls.httpGet("http://api.openweathermap.org/data/2.5/forecast/daily?q=Aveiro&units=metric&cnt=7&lang=pt&APPID=f68b13ab43f8d29d4ba7979c2c09695d");
        }


        @Override
        protected void onPostExecute(String s) {
            Log.d("AVEIRO","Conteudo web: "+s);

            try{

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                jsonObject = jsonArray.getJSONObject(0)
                        .getJSONObject("temp");

                Double currentTmp = jsonObject.getDouble("day");

                TextView tvAux = (TextView) findViewById(R.id.tv_current_temp);

                tvAux.setText(currentTmp+"º");



            }catch (Exception e){
                Log.e("AVEIRO","Erro a processar conteúdo. E.: "+e.getMessage());
            }
        }
    }


}