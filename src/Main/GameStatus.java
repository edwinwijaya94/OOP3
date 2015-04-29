package Main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
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

class ScoreComparator<Employee> implements Comparator<HighScoreTuple> {

    public int compare(HighScoreTuple score1, HighScoreTuple score2){
       if(score1.getScore() <  score2.getScore()) {return -1;}
       if(score1.getScore() ==  score2.getScore()) {
           if (score1.getId() < score2.getId()) {return -1;}
           else {return 0;}
       }
       return 1;
    }
}

public class GameStatus {
    // attributes
    private int score;
    private String playerName;
    private String highscoreFileName = "data/highscore.dat";
    public ArrayList<HighScoreTuple> highScore;
    
    public GameStatus(){
        score = 0;
        playerName = "Player";
    }
    public GameStatus(int score, String name){
        this.score = score;
        this.playerName = name;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int x){
        score = x;
    }
    public void addScore(int x){
        score += x;
        GameLayout.getInstance().getScoreLabel().setText(new Integer(score).toString());
    }
    public String getPlayerName(){
        return playerName;
    }
    public void  setPlayerName(String name){
        playerName =  name;
        GameLayout.getInstance().getPlayerNameLabel().setText(name);
        playerName = name;
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
    
    public static void main(String[] args)
    {
        GameStatus gs = new GameStatus();
        gs.getHighScoreFromFile();
        //gs.addHighScore("Vicko Novianto", 999999);
        //gs.addHighScore("Elvan Owen", 124);
        //gs.addHighScore("Jessica", 547);
        //gs.addHighScore("Edwin Wijaya", 312);
        System.out.println(gs.highScore);
        gs.writeHighScoretoFile();
    }
}
