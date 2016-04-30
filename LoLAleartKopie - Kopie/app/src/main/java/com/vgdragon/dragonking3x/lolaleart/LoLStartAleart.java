package com.vgdragon.dragonking3x.lolaleart;

import android.annotation.TargetApi;
import android.os.Build;

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
    private MainActivity ma;

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
    private PrintWriter out;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void run(){

        String IP = this.ip;
        int port = 20500;
        String gameStart = "";
        System.out.println("DDest");

                try (
                        Socket socket = new Socket(IP, port);
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        //BufferedReader send = new BufferedReader(new InputStreamReader(System.in));
                ) {

                    setOut(out);

                    setConnecting(1);
                    ma.setButtonTxt("Disconnect");
                    ma.setGameInfoTxt("");
                    ma.setConnectTxtTxt("CONNECTED");
                    ma.setConnectButton(true);


                    while (getExit() == 0) {

                        gameStart = in.readLine();
                        ma.setGameInfoTxt(gameStart);

                        if (gameStart.equalsIgnoreCase("Game Start")) {
                            ma.playSound();
                        }

                    }

                    setConnecting(0);
                    ma.setConnectTxtTxt("Disconnected");
                    ma.setButtonTxt("Connect");
                    in.close();
                    out.close();
                    socket.close();


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    public void updateConnectTxt(String text){
        ma.setConnectTxtTxt(text);

    }




}
