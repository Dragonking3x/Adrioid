package com.vgdragon.dragonking3x.lolaleart;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
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



    public boolean isConnectButton() {
        return connectButton;
    }

    public void setConnectButton(boolean connectButton) {
        this.connectButton = connectButton;
    }

    private boolean connectButton = false;

    private boolean uiStart = true;

    //Ringtone sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //try{
        //    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //    this.sound = RingtoneManager.getRingtone(getApplicationContext(), notification);
        //} catch (Exception e){
//
        //}




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
                    lol.setIAlive(1);
                    final Thread t = new Thread(lol);
                    t.start();
                }


                //if (!getT().isAlive()) {
                //    if (lol.getIAlive() == 0) {
                //        lol.setIAlive(1);
                //        t.start();
                //    }
//
//
                //}




            }


        });

        System.out.println("WWW");




    }





    protected void playSound() {
        //sound = MediaPlayer.create(this, R.raw.sound);
        //sound.setAudioStreamType(AudioManager.STREAM_MUSIC);


        //sound.start();




        //this.sound.play();
        setNotifiaktionsBar();
    }

    public void setNotifiaktionsBar(){

        Intent notificationTintent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationTintent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setContentTitle("LoL")
                .setContentText("Game Start")
                .setContentIntent(pendingIntent);

        Notification notification = mBuilder.build();

        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_SOUND;

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notification.tickerText = "LoL \n Game Start"; //edit for custam text

        /////

        //Intent notificationIntent = new Intent(this, MainActivity.class);
        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
        //        notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
        //NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
//
        //        .setContentTitle("my title")
        //        .setContentText("my content")
        //        .setContentIntent(pendingIntent);
        //Notification notification = mBuilder.build();
        //// default phone settings for notifications
        //notification.defaults |= Notification.DEFAULT_VIBRATE;
        //notification.defaults |= Notification.DEFAULT_SOUND;
//
        //// cancel notification after click
        //notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //// show scrolling text on status bar when notification arrives
        //notification.tickerText = "\n";

        // notifiy the notification using NotificationManager


    }





    protected void setGameinfo(String text) {
        this.setGameinfo = text;


    }

    protected void setConnectTxt(String text) {
        this.setConnectTxt = text;
    }




}