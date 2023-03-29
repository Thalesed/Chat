/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat;

/**
 *
 * @author alunos
 */
import java.io.InputStream;
import java.util.Scanner;
public class Recebedor implements Runnable {
    
   private InputStream servidor;
 
   public Recebedor(InputStream servidor) {
     this.servidor = servidor;
   }
 
   @Override
   public void run() {
     // recebe msgs do servidor e imprime na tela
     Scanner s = new Scanner(this.servidor);
     while (s.hasNextLine()) {
         String line  = s.nextLine();
       System.out.println(line);
       Interface.textArea.setText(Interface.textArea.getText() + line + "\n");
     }
   }
}
