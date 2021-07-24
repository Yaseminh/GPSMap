package com.yaseminsolmaz.haritagps;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button getButton;
    static String longitudey, latitudex;
    static  public ModelView returnmodel;
    PostandGetLocation postandGetLocation;
    static public TextView cText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //post butonu
        button = findViewById(R.id.button);
        cText = findViewById(R.id.cText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        //Class
        postandGetLocation = new PostandGetLocation();

        //getbutonu
        getButton = findViewById(R.id.button2);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getmetod call
                postandGetLocation.GetLocation(getApplicationContext(), "https://projectdenememm20190917064057.azurewebsites.net/api/GpsCordinates/get");
                //  System.out.println("returnmodel.latitude"+returnmodel.latitude);

            }
        });
    }

    public void printScreen(ModelView returnmodel) {
        cText.setText("latitude:" + returnmodel.getLatitude() + "\nlongitude:" + returnmodel.getLongitude());
    }

}
