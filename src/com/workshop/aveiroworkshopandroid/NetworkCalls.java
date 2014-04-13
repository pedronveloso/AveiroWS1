package com.workshop.aveiroworkshopandroid;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkCalls {



    public static String httpGet(String url){
        try{
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.connect();
            InputStream is = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String response ="", data;
            while((data = reader.readLine())!=null){
                response +=data+"\n";
            }

            return response;
        }catch (Exception ignored){
            return null;

        }
    }
}
