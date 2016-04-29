package com.vgdragon.dragonking3x.lolaleart;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Dragonking3x on 27.04.2016.
 */
public class LoLStartAleart implements Runnable{





    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    Context context;

    public TextView getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(TextView gameInfo) {
        this.gameInfo = gameInfo;
    }

    TextView gameInfo;

    public TextView getConnectTxt() {
        return connectTxt;
    }

    public void setConnectTxt(TextView connectTxt) {
        this.connectTxt = connectTxt;
    }

    TextView connectTxt;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String ip;



    public MainActivity getMa() {
        return ma;
    }

    public void setMa(MainActivity ma) {
        this.ma = ma;
    }

    MainActivity ma;

    public int getIAlive() {
        return iAlive;
    }

    public void setIAlive(int iAlive) {
        this.iAlive = iAlive;
    }

    private int iAlive = 0;

    public int getConnecting() {
        return connecting;
    }

    public void setConnecting(int connecting) {
        this.connecting = connecting;
    }

    private int connecting = 0;

    public int getExit() {
        return exit;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }

    private int exit = 0;


    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    PrintWriter out;







    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void run(){
        setIAlive(1);


        String IP = this.ip;
        int port = 20500;
        String gameStart = "";
        System.out.println("DDest");

        while (true) {





            if(connecting == 1) {


                try (

                        Socket socket = new Socket(IP, port);
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        //BufferedReader send = new BufferedReader(new InputStreamReader(System.in));


                ) {
                    setOut(out);

                    System.out.println("DDest02");


                    //updateConnectTxt("CONNECTED");

                    ma.setConnectTxt("CONNECTED");
                    //connectTxt.setText("CONNECTED");
                    Thread.sleep(1000);


                    while (getExit() == 0) {
                        gameStart = in.readLine();
                        ma.setGameinfo(gameStart);
                        if (gameStart.equalsIgnoreCase("Game Start")) {
                            ma.playSound();

                        }


                    }
                    setConnecting(0);
                    System.out.println("exit");


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    setConnecting(0);

                } catch (IOException e) {
                    e.printStackTrace();


                } catch (InterruptedException e) {
                    e.printStackTrace();


                }
            }

        }



    }
    public void updateConnectTxt(String text){
        ma.setConnectTxt(text);

    }


}
