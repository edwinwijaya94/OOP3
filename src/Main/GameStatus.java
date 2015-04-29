package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */

public class GameStatus {
    // attributes
    private int score;
    private String playerName;
    
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
    }
}