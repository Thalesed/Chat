import java.io.*;
import java.net.*;
import java.awt.Color;
import javax.swing.JOptionPane;
public class Interface extends javax.swing.JFrame {

    private Socket socket;
    private BufferedReader buffR;
    private BufferedWriter buffW;
    private String nome;
    private static String ip;
    private static int port;
    
    public Interface(Socket cliente, String ip, int port, String nome, BufferedReader buffR, BufferedWriter buffW, boolean dark) {
        Interface.ip = ip;
        Interface.port = port;
        this.nome = nome;
        this.buffR = buffR;
        this.buffW = buffW;
        initComponents();
        getRootPane().setDefaultButton(this.send);
        this.darkM(dark);
        Interface.ipLabel.setText(Interface.ipLabel.getText() + Interface.ip);
        Interface.portLabel.setText(Interface.portLabel.getText() + Interface.port);
        try{
            buffW.write(nome);
            buffW.newLine();
            buffW.flush();
            Recebedor r = new Recebedor(cliente.getInputStream());
            new Thread(r).start();
        }catch(IOException e){}
    }

    @SuppressWarnings("unchecked")                         
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        ipLabel = new javax.swing.JLabel();
        portLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        msg = new javax.swing.JTextField();
        del = new javax.swing.JButton();

        setBackground(java.awt.Color.darkGray);

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        ipLabel.setForeground(new java.awt.Color(204, 204, 204));
        ipLabel.setText("ip:");

        portLabel.setForeground(new java.awt.Color(204, 204, 204));
        portLabel.setText("port:");

        jLabel1.setFont(new java.awt.Font("TlwgTypewriter", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Chat Online");

        send.setBackground(new java.awt.Color(51, 153, 255));
        send.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        

        msg.setFont(new java.awt.Font("Purisa", 0, 15)); // NOI18N
        

        del.setBackground(new java.awt.Color(255, 0, 0));
        del.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        del.setText("Del");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(msg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ipLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(portLabel))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(send)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(del)))
                .addGap(16, 16, 16))
        );

        pack();
    }                      

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {                                     
        try{
            String line = this.msg.getText();
            buffW.write(nome + ": " + line);
            buffW.newLine();
            buffW.flush();
            this.msg.setText("");
            Interface.textArea.setLineWrap(rootPaneCheckingEnabled);
            Interface.textArea.setWrapStyleWord(rootPaneCheckingEnabled);
            Interface.textArea.setText(Interface.textArea.getText() + "Você: " + line + "\n");
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Impossivel conectar ao servidor", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        
    }                                    

                          

    public void darkM(boolean dark){
        Color corDeFundo;
        if(dark == false){
            corDeFundo = new Color(0, 0, 0);
            getContentPane().setBackground(corDeFundo);
            this.jLabel1.setForeground(new Color(208, 208, 208));
            Interface.ipLabel.setForeground(new Color(208, 208, 208));
            Interface.portLabel.setForeground(new Color(208, 208, 208));
        }else {
            corDeFundo = new Color(240, 240, 240);
            getContentPane().setBackground(corDeFundo);
            this.jLabel1.setForeground(new Color(0, 0, 0));
            Interface.ipLabel.setForeground(new Color(0, 0, 0));
            Interface.portLabel.setForeground(new Color(0, 0, 0));
        }
    }
     private void delActionPerformed(java.awt.event.ActionEvent evt) {                                    
        Interface.textArea.setText("");
    }      
    
    
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }   
                  
    private javax.swing.JButton del;
    private static javax.swing.JLabel ipLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField msg;
    private static javax.swing.JLabel portLabel;
    private javax.swing.JButton send;
    public static javax.swing.JTextArea textArea;                 
}
