import java.io.*;
import java.net.*;
import java.awt.Color;
import javax.swing.JOptionPane;
public class Enter extends javax.swing.JFrame {


    private Socket socket;
    private BufferedReader buffR;
    private BufferedWriter buffW;
    private String nome;
    private boolean dark = false;
    private boolean entrou = false;
    Interface inter;
    public Enter() {
        initComponents();
        getRootPane().setDefaultButton(this.enterBtn);
        Color corDeFundo = new Color(240, 240, 240);
        getContentPane().setBackground(corDeFundo);
        this.jLabel1.setForeground(new Color(0, 0, 0));
        this.jLabel2.setForeground(new Color(0, 0, 0));
        this.jLabel3.setForeground(new Color(0, 0, 0));
        this.jLabel4.setForeground(new Color(0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        ipText = new javax.swing.JTextField();
        portText = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        enterBtn = new javax.swing.JButton();
        darkMode = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.selectedForeground"));
        setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("TlwgTypewriter", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Chat Online");

        ipText.setText("127.0.0.1");

        portText.setText("6969");

        name.setText("user");

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("IP:");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Port:");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Nome:");

        enterBtn.setBackground(new java.awt.Color(51, 153, 255));
        enterBtn.setText("Enter");
        enterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterBtnActionPerformed(evt);
            }
        });

        darkMode.setFont(new java.awt.Font("Liberation Sans", 0, 8)); // NOI18N
        darkMode.setText("DM");
        darkMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkModeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ipText, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(portText, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(darkMode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(enterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(darkMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(enterBtn)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }

    private void enterBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            this.socket = new Socket(ipText.getText(), Integer.parseInt(portText.getText()));
            this.entrou = true;
            this.buffW = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.buffR = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.nome = this.name.getText();
            boolean d = (this.dark == false);
            this.inter = new Interface(this.socket, this.ipText.getText(), Integer.parseInt(portText.getText()), this.nome, this.buffR, this.buffW, d);
            this.inter.setVisible(true);
        }catch(IOException e){
        JOptionPane.showMessageDialog(null, "Servidor não Existe", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        this.setVisible(false);
    }

    private void darkModeActionPerformed(java.awt.event.ActionEvent evt) {
        if(entrou){
            this.inter.darkM(this.dark);
        }
        Color corDeFundo;
        if(this.dark == false){
            corDeFundo = new Color(0, 0, 0);
            getContentPane().setBackground(corDeFundo);
            this.dark = true;
            this.jLabel1.setForeground(new Color(208, 208, 208));
            this.jLabel2.setForeground(new Color(208, 208, 208));
            this.jLabel3.setForeground(new Color(208, 208, 208));
            this.jLabel4.setForeground(new Color(208,208,208));
        }else {
            corDeFundo = new Color(240, 240, 240);
            getContentPane().setBackground(corDeFundo);
            this.jLabel1.setForeground(new Color(0, 0, 0));
            this.jLabel2.setForeground(new Color(0, 0, 0));
            this.jLabel3.setForeground(new Color(0, 0, 0));
            this.jLabel4.setForeground(new Color(0, 0, 0));
            this.dark = false;
        }
    
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
            java.util.logging.Logger.getLogger(Enter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Enter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Enter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Enter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    private javax.swing.JButton darkMode;
    private javax.swing.JButton enterBtn;
    private javax.swing.JTextField ipText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField portText;
    
}
