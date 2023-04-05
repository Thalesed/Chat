

import java.io.IOException;
import java.net.*;

public class Servidor {
    private ServerSocket server;

    public static void main(String[] args) throws IOException{
        ServerSocket serverS = new ServerSocket(6969);
        Servidor server = new Servidor(serverS);
        server.start();
    }

    public Servidor(ServerSocket server){
        this.server = server;
    }

    public void start() throws IOException{
        while(!server.isClosed()){
            Socket cliente = server.accept();
            System.out.println("Novo cliente");
            new TratamentoClasse(cliente).start();
        }
    }

    public void close() throws IOException{
        if(server!=null){
            server.close();
        }
    }
}
