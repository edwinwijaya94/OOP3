/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jessica
 */

class HighScoreTuple implements Serializable
{
    private String playerName;
    private int score;
    private int id;
    
    public HighScoreTuple(String playerName, int score, int id)
    {
        this.playerName = playerName;
        this.score = score;
        this.id = id;
    }
    
    public String getPlayerName()
    {
        return playerName;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setScore(int newScore)
    {
        score = newScore;
    }
    
    public void setPlayerName(String newPlayerName)
    {
        playerName = newPlayerName;
    }
    
    public String toString() {
        return getPlayerName() + " - " + getScore();
    }
}

class ScoreComparator implements Comparator<HighScoreTuple> {
    public int compare(HighScoreTuple score1, HighScoreTuple score2){
       if(score1.getScore() <  score2.getScore()) {return -1;}
       if(score1.getScore() ==  score2.getScore()) {
           if (score1.getId() < score2.getId()) {return -1;}
           else {return 0;}
       }
       return 1;
    }
}

public class HighScore extends javax.swing.JFrame {

    private static HighScore instance;
    private String highscoreFileName = "data/highscore.dat";
    public ArrayList<HighScoreTuple> highScore = new ArrayList<HighScoreTuple>();
    private JLabel jLabel1;
    private JLabel jLabel2;
    
    /**
     * Creates new form HighScore
     */
    private HighScore() {
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
                ImageIcon icon2 = new ImageIcon("image/score.png");
                Image image = icon2.getImage();
                image = image.getScaledInstance(panelWidth, panelHeight,  java.awt.Image.SCALE_SMOOTH); 
                icon2 = new ImageIcon(image);
                labelb.setIcon(icon2);
                labelb.setSize(panelWidth,panelHeight);
                labelb.setLocation(0,0);
                jLayeredPane1.add(labelb, 3);
            }

            public void componentHidden(ComponentEvent e) {}
            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
        
        });
        //displayHighScore();
    }
    
    public void displayHighScore() {
        if (highScore != null)
        {
            getHighScoreFromFile();   
            if ((jLabel1 == null) && (jLabel2 == null))
            {
                jLabel1 = new JLabel();        
                jLabel2 = new JLabel(); 
            }
            else 
            {
                jLabel1.setFont(jLabel1.getFont().deriveFont((float)(jLabel1.getFont().getSize()-12)));
                jLabel2.setFont(jLabel2.getFont().deriveFont((float)(jLabel2.getFont().getSize()-12)));
                jLayeredPane1.remove(jLabel1);
                jLayeredPane1.remove(jLabel2);   
            }
            int i = highScore.size()-1;
            int o = 0;
            StringBuilder hs = new StringBuilder();
            StringBuilder hs2 = new StringBuilder();
            hs.append("<html><pre>");
            hs2.append("<html><pre>");
            while ((i >= 0) && (o < 10)) {
                hs.append((o+1) + ". " + highScore.get(i).getPlayerName() + "<br />"); 
                hs2.append("" + highScore.get(i).getScore() + "<br />");
                i--;
                o++;
            }
            hs.append("</pre></html>");
            hs2.append("</pre></html>");
            jLabel1.setText(hs.toString());
            jLabel1.setSize(320,500);
            jLabel1.setVisible(true);
            jLabel2.setLocation((int)jLayeredPane1.getLocation().getX() + jLayeredPane1.getWidth() - 100, (int)jLayeredPane1.getLocation().getY()+120);
            jLabel1.setLocation((int)jLabel2.getLocation().getX() - 400, (int)jLayeredPane1.getLocation().getY()+120);
            jLabel2.setSize(300,500);
            jLabel2.setVisible(true);
            jLabel2.setText(hs2.toString());
            jLabel1.setVerticalAlignment(SwingConstants.TOP);
            jLabel1.setForeground(Color.BLACK);
            jLabel1.setFont(jLabel1.getFont().deriveFont((float)(jLabel1.getFont().getSize()+12)));
            jLabel1.setFont(jLabel1.getFont().deriveFont(Font.BOLD)); 
            jLabel2.setVerticalAlignment(SwingConstants.TOP);
            jLabel2.setForeground(Color.BLACK);
            jLabel2.setFont(jLabel2.getFont().deriveFont((float)(jLabel2.getFont().getSize()+12)));
            jLabel2.setFont(jLabel2.getFont().deriveFont(Font.BOLD)); 
            jLayeredPane1.add(jLabel1, 1);
            jLayeredPane1.add(jLabel2, 0);
        }
    }
    
    public void showHighScoreFrame()
    {
        this.setVisible(true);
        displayHighScore();
    }
    
    public ArrayList<HighScoreTuple> getHighScore()
    {
        return highScore;
    }
    
    public synchronized static HighScore getInstance()
    {
        if (instance == null)
        {
            instance = new HighScore();
            instance.writeHighScoretoFile();
        }
        return instance;
    }
    
    public void removeAllHighScore()
    {
        highScore.clear();
        writeHighScoretoFile();
    }
    
    public void addHighScore(String playerName, int score)
    {
        highScore.add(new HighScoreTuple(playerName,score,highScore.size()));
        Collections.sort(highScore,new ScoreComparator());
    }
    
    public void getHighScoreFromFile()
    {
        try
        {
            FileInputStream fis = new FileInputStream(highscoreFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            highScore = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch(EOFException ioe){
             highScore= new ArrayList<>();
             return;
        }catch(IOException ioe){
             ioe.printStackTrace();
             return;
        }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return;
        }
        /*
        for(HighScoreTuple tmp: highScore){
            System.out.println(tmp.getPlayerName() + ' ' + tmp.getScore());
        }*/
    }
    
    public void writeHighScoretoFile()
    {
       try{
         FileOutputStream fos= new FileOutputStream(highscoreFileName);
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(highScore);
         oos.close();
         fos.close();
       }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        BackToMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BackToMenu.setFont(new java.awt.Font("Kartika", 1, 14)); // NOI18N
        BackToMenu.setText("Back To Menu");
        BackToMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackToMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(706, Short.MAX_VALUE)
                .addComponent(BackToMenu)
                .addGap(31, 31, 31))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(509, Short.MAX_VALUE)
                .addComponent(BackToMenu)
                .addContainerGap())
        );
        jLayeredPane1.setLayer(BackToMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackToMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToMenuActionPerformed
        // TODO add your handling code here:
        //Menu stateMenu = Menu.getInstance();
        this.setVisible(false);
        Menu.getInstance().showMenuFrame();
    }//GEN-LAST:event_BackToMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToMenu;
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration//GEN-END:variables
}
