package com.mycompany.chatonline;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cli {
    private Socket cliente;
    private BufferedReader buffR;
    private BufferedWriter buffW;
    private String nome;
    private int port;
    private String ip;
    private String text = "";
    private static boolean send = false;
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setPort(int port){
        this.port = port;
    }
    public void setIp(String ip){
        this.ip = ip;
    }

    public Cli(String ip, int port, String nome) throws UnknownHostException, IOException{
        this.setIp(ip);
        this.setPort(port);
        this.setNome(nome);
        Socket cliente = new Socket(this.ip, this.port);
        try{
            this.cliente = cliente;
            this.buffW = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            this.buffR = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (IOException e){
            fechaCliente(cliente, buffR, buffW);
        }
    }
    
    public void setSend(boolean b){
        Cli.send = b;
    }

    public void mandarLine(String line){
        try{
               //buffW.write(nome);
               buffW.newLine();
               buffW.flush();

               Scanner scan = new Scanner(System.in);
                //String line = scan.nextLine();
                if(Cli.send == true){
                    buffW.write("\u001B[31m" + nome + ": " + line + "\u001B[m");
                    //buffW.newLine();
                    buffW.flush();
                    Cli.send = false;
                }
               //scan.close();
           } catch (IOException e){
               fechaCliente(cliente, buffR, buffW);
           }
}
    public String getText(){
        return this.text;
    }

    public void esperaLine(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                String line;
                while(cliente.isConnected()){
                    try{
                        line = buffR.readLine();
                        System.out.println(line);
                        text += line;
                    } catch (IOException e){
                        fechaCliente(cliente, buffR, buffW);
                    }
                }
            }
        }).start();
    }

    public void fechaCliente(Socket cliente, BufferedReader buffR, BufferedWriter buffW){
        try{
            if(buffR != null) buffR.close();
            if(buffW != null) buffW.close();
            if(cliente != null) cliente.close();
        } catch(IOException e){}
    }
}
