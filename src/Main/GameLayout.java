package Main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import Main.Background;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */

class Passer
{
    public volatile String word = "default";
}

public class GameLayout extends javax.swing.JFrame {
    
    //attribute 
    private static GameLayout instance;
    private static int animalSize = 5;
    static Animal[] animals = new Animal[animalSize];
    //Background background;
    TypeHandler typeHandler;
    GameStatus gameStatus;
    public volatile Passer passer = new Passer();
    Object correctLock = new Object();
    int totalCorrectWords = 0;
    Background bg = new Background(this);
    Clip backgroundClip;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
	//bg.paint(g2d);
    }
    
    public int getTotalCorrectWords()
    {
        synchronized(correctLock)
        {
            return totalCorrectWords;
        }
    }
    
    public void addTotalCorrectWords()
    {
        synchronized(correctLock)
        {
            totalCorrectWords++;
        }
    }
    
    /**
     * Creates new form GameLayout
     */
    private GameLayout() {
        initComponents();
        
                addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                        
                        //System.out.println(e.getComponent().toString());
    			//System.out.println(e.getComponent().getSize().height);
                        int panelHeight = e.getComponent().getSize().height;
                        //System.out.println(panelHeight);
                        int panelWidth = e.getComponent().getSize().width;
                        //System.out.println(panelWidth);
                        
                        
                        JLabel labelb = new JLabel();
                        ImageIcon icon2 = new ImageIcon("image/gamelayout.png");
                        Image image = icon2.getImage();
                        image = image.getScaledInstance(panelWidth, panelHeight,  java.awt.Image.SCALE_SMOOTH); 
                        icon2 = new ImageIcon(image);
                        labelb.setIcon(icon2);
                        labelb.setSize(panelWidth, panelHeight);
                        labelb.setLocation(0,0);
                        jLayeredPane1.add(labelb, 1);
    		}

    		public void componentHidden(ComponentEvent e) {}

    		public void componentMoved(ComponentEvent e) {}

    		public void componentShown(ComponentEvent e) {}
        
        });
        
        getTextField().getDocument().addDocumentListener(new DocumentListener() {

        public void warn() {
           passer.word = getTextField().getText();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            warn();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            warn();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            warn();
        }
      });
        startGameButton.requestFocusInWindow();
    }
    
    public void debug(String input)
    {
        jLabel2.setText(input);
    }
    
    public GameStatus getGameStatus()
    {
        return gameStatus;
    }
    
    public synchronized static GameLayout getInstance()
    {
        if (instance == null)
        {
            instance = new GameLayout();
        }
        return instance;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        startGameButton = new javax.swing.JButton();
        backToMenuButton = new javax.swing.JButton();
        inputField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        resumeButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        startGameButton.setLabel("Start Game");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        backToMenuButton.setLabel("Back To Menu");
        backToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenuButtonActionPerformed(evt);
            }
        });

        inputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });
        inputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputFieldKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Kartika", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 61, 79));
        jLabel1.setText("Player Name :");

        playerNameLabel.setFont(new java.awt.Font("Kartika", 1, 14)); // NOI18N
        playerNameLabel.setText("NN");

        jLabel3.setFont(new java.awt.Font("Kartika", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 61, 79));
        jLabel3.setText("Score :");

        scoreLabel.setFont(new java.awt.Font("Kartika", 1, 14)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(204, 0, 0));
        scoreLabel.setText("0");

        jLabel2.setText("jLabel2");

        resumeButton.setText("Resume");
        resumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resumeButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerNameLabel)
                        .addGap(108, 108, 108)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(startGameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(pauseButton)
                        .addGap(37, 37, 37)
                        .addComponent(resumeButton)
                        .addGap(45, 45, 45)
                        .addComponent(backToMenuButton))
                    .addComponent(inputField)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(scoreLabel)
                    .addComponent(jLabel2)
                    .addComponent(playerNameLabel)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backToMenuButton)
                    .addComponent(startGameButton)
                    .addComponent(resumeButton)
                    .addComponent(pauseButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputFieldActionPerformed

    private void inputFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputFieldKeyReleased
        // TODO add your handling code here:
        passer.word = getTextField().getText();
    }//GEN-LAST:event_inputFieldKeyReleased

    private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMenuButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        //Menu.setVisible(true);
    }//GEN-LAST:event_backToMenuButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        // TODO add your handling code here:
            for (int i=0;i<animalSize;i++)
            {
                try
                {
                    animals[i].getThread().interrupt();
                }catch(Exception e){}
            }
            pauseButton.setEnabled(false);
            resumeButton.setEnabled(true);
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void resumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resumeButtonActionPerformed
        // TODO add your handling code here:
            for (int i=0;i<animalSize;i++)
            {
                try
                {
                    animals[i].getLabel().setVisible(true);
                    animals[i].move();
                }catch(Exception e){}
            }
            pauseButton.setEnabled(true);
            resumeButton.setEnabled(false);
    }//GEN-LAST:event_resumeButtonActionPerformed

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
        
        
        
        startGameButton.setEnabled(false);
        resumeButton.setEnabled(false);
        inputField.requestFocusInWindow();
        jPanel1.setLayout(null);
        gameStatus = new GameStatus();
        
        // ask for player's name
        String name = JOptionPane.showInputDialog(this, "What's your name?");

        // get the user's input. note that if they press Cancel, 'name' will be NN
        if(name == null){
            //playerNameLabel.setText("NN");
            getGameStatus().setPlayerName("NN");
        }
        else{
            //playerNameLabel.setText(name);
            getGameStatus().setPlayerName(name);
        }
        
        
        for(int i = 0; i < 5; i++)
        {
            animals[i] = AnimalFactory.getInstance().getAnimal();
            animals[i].draw(i);
        }
        jPanel1.revalidate();
        jPanel1.repaint();
        typeHandler = new TypeHandler(passer);
        startBackgroundClip();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

                getInstance().setVisible(true);
            
        
        
        
        while(true)
        {
            for (int i=0;i<animalSize;i++)
            {
                try
                {
                    animals[i].getThread().join();
                }catch(Exception e){}
            }
        }  
    }
    
    public JLayeredPane getPanel() {
        return jLayeredPane1;
    }
    
    public Animal[] getAnimals() {
        return animals;
    }
    
    public JTextField getTextField() {
        return inputField;
    }

    public JLabel getPlayerNameLabel(){
        return playerNameLabel;
    }
    
    public JLabel getScoreLabel(){
        return scoreLabel;
    }
    
    public void startBackgroundClip()
    {
        String path = "music/samba.wav";
        try{
            File audioFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            backgroundClip = (Clip) AudioSystem.getLine(info);

            backgroundClip.open(audioStream);            
            backgroundClip.start();
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void stopBackgroundClip()
    {
        backgroundClip.stop();
    }
    
    public JButton getStartGameButton(){
        return startGameButton;
    }
    
    public int getAnimalSize()
    {
        return animalSize;
    }
    public TypeHandler getTypeHandler()
    {
        return typeHandler;
    }

            
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToMenuButton;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton pauseButton;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JButton resumeButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton startGameButton;
    // End of variables declaration//GEN-END:variables
}
