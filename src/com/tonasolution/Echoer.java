package com.tonasolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket){
        System.out.println("The client connected");
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
            );
            PrintWriter output = new PrintWriter(this.socket.getOutputStream(), true);

            while(true){
                String echoString = input.readLine();
                System.out.println("the text sent : " + echoString);

                if(echoString.equals("exit")){
                    break;
                }

                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                output.println(echoString);
            }
        } catch(IOException e){

        } finally {
            try {
                this.socket.close();
                System.out.println("socket closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
