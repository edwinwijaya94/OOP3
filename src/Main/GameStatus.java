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
    
    public void addHighScore(String playerName, int score)
    {
        highScore.add(new HighScoreTuple(playerName,score));
    }
    
    private void getHighScoreFromFile()
    {
        try
        {
            FileInputStream fis = new FileInputStream(highscoreFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            highScore = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe){
             ioe.printStackTrace();
             return;
        }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return;
        }
        
        if (highScore == null)
        {
            highScore= new ArrayList<>();
        }
        
        for(HighScoreTuple tmp: highScore){
            System.out.println(tmp.getPlayerName() + ' ' + tmp.getScore());
        }
    }
    
    private void writeHighScoretoFile()
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
}
