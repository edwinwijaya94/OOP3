/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
    public ArrayList<HighScoreTuple> highScore;
    
    /**
     * Creates new form HighScore
     */
    private HighScore() {
        this.setVisible(true);
        initComponents();
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

        jPanel1 = new javax.swing.JPanel();
        BackToMenu = new javax.swing.JButton();
        scoreBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        BackToMenu.setFont(new java.awt.Font("Kartika", 1, 14)); // NOI18N
        BackToMenu.setText("Back To Menu");
        BackToMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackToMenuActionPerformed(evt);
            }
        });
        jPanel1.add(BackToMenu);
        BackToMenu.setBounds(643, 480, 140, 29);

        scoreBackground.setIcon(new javax.swing.ImageIcon("image/score.png")); // NOI18N
        jPanel1.add(scoreBackground);
        scoreBackground.setBounds(0, 10, 800, 510);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackToMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToMenuActionPerformed
        // TODO add your handling code here:
        Menu stateMenu = Menu.getInstance();
        this.dispose();
    }//GEN-LAST:event_BackToMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel scoreBackground;
    // End of variables declaration//GEN-END:variables
}
