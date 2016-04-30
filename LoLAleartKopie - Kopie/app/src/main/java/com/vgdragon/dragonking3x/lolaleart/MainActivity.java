package com.vgdragon.dragonking3x.lolaleart;

import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView gameInfo;
    EditText ipAdress;
    TextView connectTxt;
    MainActivity ma = this;
    //MediaPlayer sound;
    String setGameinfo = "";

    public String getSetConnectTxt() {
        return setConnectTxt;
    }
    public void setSetConnectTxt(String setConnectTxt) {
        this.setConnectTxt = setConnectTxt;
    }
    String setConnectTxt = "";

    public String getButtonTxt() {
        return buttonTxt;
    }
    public void setButtonTxt(String buttonTxt) {
        this.buttonTxt = buttonTxt;
    }
    String buttonTxt = "";

    public boolean isConnectButton() {
        return connectButton;
    }
    public void setConnectButton(boolean connectButton) {
        this.connectButton = connectButton;
    }
    private boolean connectButton = false;

    public String getGameInfoTxt() {
        return gameInfoTxt;
    }
    public void setGameInfoTxt(String gameInfoTxt) {
        this.gameInfoTxt = gameInfoTxt;
    }
    private String gameInfoTxt = "";


    public String getConnectTxtTxt() {
        return connectTxtTxt;
    }
    public void setConnectTxtTxt(String connectTxtTxt) {
        this.connectTxtTxt = connectTxtTxt;
    }
    private String connectTxtTxt = "";

    Ringtone sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set sound for game start
        try{
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            this.sound = RingtoneManager.getRingtone(getApplicationContext(), notification);
        } catch (Exception e){

        }

        final Button connect = (Button) findViewById(R.id.connectButton);
        ipAdress = (EditText) findViewById(R.id.ipTxtField);
        gameInfo = (TextView) findViewById(R.id.gameInfoTxt);
        connectTxt = (TextView) findViewById(R.id.connectTxt);

        setConnectTxtTxt("");
        setGameInfoTxt("");
        setButtonTxt("CONNECT");
        connect.setBackgroundColor(Color.rgb(0,250,0));

        final LoLStartAleart lol = new LoLStartAleart();

        lol.setIp(ipAdress.getText().toString());
        lol.setMa(ma);

        final Thread txtUpdate = new Thread() {
            public void run() {
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(lol.getConnecting() == 0){
                                    connect.setBackgroundColor(Color.rgb(0,250,0));
                                }else{
                                    connect.setBackgroundColor(Color.rgb(250,0,0));
                                }

                                connectTxt.setText(getConnectTxtTxt());
                                gameInfo.setText(getGameInfoTxt());
                                connect.setText(getButtonTxt());

                        }
                    });

                        Thread.sleep(200);
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
                    setConnectButton(false);

                    lol.getOut().println("exit");
                }else{

                    lol.setExit(0);

                    final Thread t = new Thread(lol);
                    t.start();
                }

            }


        });

    }

    protected void playSound() {

        //sound = MediaPlayer.create(this, R.raw.sound);
        //sound.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //sound.start();

        this.sound.play();

    }

}