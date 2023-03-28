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
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setPort(int port){
        this.port = port;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public int getPort(){
        return this.port;
    }
    public String getIp(){
        return this.ip;
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
    

    public void mandarLine(String line){
        try{
               //buffW.write(nome);
               buffW.newLine();
               buffW.flush();

               Scanner scan = new Scanner(System.in);
                //String line = scan.nextLine();
                buffW.write(nome + ": " + line);
                //buffW.newLine();
                buffW.flush();
                    
               //scan.close();
           } catch (IOException e){
               fechaCliente(cliente, buffR, buffW);
           }
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
                        Chat.addText(line+"\n");
                    } catch (IOException e){
                        System.out.println("deu errado");
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
