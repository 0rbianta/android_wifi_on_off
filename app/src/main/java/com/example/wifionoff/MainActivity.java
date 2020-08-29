package com.example.wifionoff;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Boolean force;
    private Thread thr;
    private WifiManager wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifi = (WifiManager) getApplicationContext().getSystemService(MainActivity.this.WIFI_SERVICE);



        thr =  new Thread(){
            @Override
            public void run(){
                while (true){
                    if(force){
                        wifi.setWifiEnabled(true);
                    }else if(!force){
                        wifi.setWifiEnabled(false);
                    }
                }
            }
        };

    }

    public void btnEnableClick(View v){

        wifi.setWifiEnabled(true); //TRUE = ENABLE || FALSE = DISABLE

    }

    public void btnDisableClick(View v){
        wifi.setWifiEnabled(false);
    }

    public void btnForceEnableClick(View v){
        force=true;
        try{thr.start();}catch (Exception e){}
    }

    public void btnForceDisableClick(View v){
        force=false;
        try{thr.start();}catch (Exception e){}
    }
    public void btnForceStopClick(View v){
        try{thr.stop();}catch (Exception e){}
    }

}