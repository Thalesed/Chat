

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class TratamentoClasse extends Thread{
    public static ArrayList<TratamentoClasse> threads = new ArrayList<TratamentoClasse>();

    private Socket cliente;
    private String nome;

    private BufferedReader  buffR;
    private BufferedWriter buffW;

    public TratamentoClasse(Socket cliente){
        try{
            threads.add(this);
            this.cliente = cliente;
            this.buffR = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            this.buffW = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            this.nome = buffR.readLine();
            mandarLine(nome + " entrou");
        }catch(IOException e){
            fechaCliente(cliente, buffR, buffW);
        }
    }

    @Override
    public void run(){
        String line;
        while(cliente.isConnected()){
            try{
                line = buffR.readLine();
                if(cliente.isClosed()) break;
                mandarLine(line);
            } catch (IOException e){
                fechaCliente(cliente, buffR, buffW);
                break;
            }
        }
    }

    public void mandarLine(String line){
        for(TratamentoClasse t: threads){
            try{
                if(!t.nome.equals(nome)){
                    BufferedWriter print = t.buffW;
                    print.write(line);
                    print.newLine();
                    print.flush();
                }
            } catch (IOException e){
                fechaCliente(cliente, buffR, buffW);
            }
        }
    }

    public void tiraThread(){
        threads.remove(this);
        mandarLine(nome + " desconectou");
    }

    public void fechaCliente(Socket cliente, BufferedReader buffR, BufferedWriter buffW){
        tiraThread();
        try{
            if(buffR != null) buffR.close();
            if(buffW != null) buffW.close();
            if(cliente != null) cliente.close();
        } catch(IOException e){}
    }
}
