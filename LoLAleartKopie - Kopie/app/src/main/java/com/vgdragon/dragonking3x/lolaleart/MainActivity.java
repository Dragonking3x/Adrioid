package com.vgdragon.dragonking3x.lolaleart;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.ContentHandler;

public class MainActivity extends AppCompatActivity {

    TextView gameInfo;
    EditText ipAdress;
    TextView connectTxt;
    TextView buttonTxT;
    MainActivity ma = this;
    MediaPlayer sound;
    String setGameinfo = "";



    public String getSetConnectTxt() {
        return setConnectTxt;
    }

    public void setSetConnectTxt(String setConnectTxt) {
        this.setConnectTxt = setConnectTxt;
    }

    String setConnectTxt = "";
    Context context = this;

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    private Thread t;

    public boolean isConnectButton() {
        return connectButton;
    }

    public void setConnectButton(boolean connectButton) {
        this.connectButton = connectButton;
    }

    private boolean connectButton = false;

    private boolean uiStart = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Button connect = (Button) findViewById(R.id.connectButton);
        ipAdress = (EditText) findViewById(R.id.ipTxtField);
        gameInfo = (TextView) findViewById(R.id.gameInfoTxt);
        connectTxt = (TextView) findViewById(R.id.connectTxt);





        setConnectTxt("Disconected");
        setGameinfo("");


        final LoLStartAleart lol = new LoLStartAleart();


        lol.setIp(ipAdress.getText().toString());
        lol.setMa(ma);
        lol.setContext(context);
        lol.setConnectTxt(connectTxt);
        final Thread t = new Thread(lol);
        setT(t);


        final Thread txtUpdate = new Thread() {
            public void run() {
                boolean b = true;
                while (b) {

                    try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {


                            connectTxt.setText(setConnectTxt);
                            gameInfo.setText(setGameinfo);


                        }
                    });

                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }







            }


        };


        txtUpdate.start();




        connect.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                if(isConnectButton()){
                    lol.setExit(1);
                    lol.setConnecting(0);
                    connect.setText("Connect");
                    setConnectButton(false);
                    lol.getOut().println("exit");
                }else{
                    lol.setConnecting(1);
                    connect.setText("Disconnect");
                    setConnectButton(true);
                }


                if (!getT().isAlive()) {
                    if (lol.getIAlive() == 0) {
                        lol.setIAlive(1);
                        t.start();
                    }


                }




            }


        });

        System.out.println("WWW");




    }





    protected void playSound() {
        sound = MediaPlayer.create(this, R.raw.sound);
        sound.start();

    }


    protected void setGameinfo(String text) {
        this.setGameinfo = text;


    }

    protected void setConnectTxt(String text) {
        this.setConnectTxt = text;
    }




}