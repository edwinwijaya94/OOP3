/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author elvan_owen
 */
public class EscapeObserver implements EventObserver{
    
    private static EscapeObserver instance;
    
    public static EscapeObserver getInstance()
    {
        if (instance == null){
            instance = new EscapeObserver();
        }
        return instance;
    }
    
    private EscapeObserver()
    {
    }
    
    @Override
    public synchronized void handle() {
        Animal[] animalList = GameLayout.getInstance().getAnimals();
        //System.out.println("a");
        if (GameLayout.getInstance().getTypeHandler().isAlive())
        {
        GameLayout.getInstance().getTypeHandler().interrupt();
        for (int i = 0; i < GameLayout.getInstance().getAnimalSize(); i++) {
        //    animalList[i].getThread().interrupt();
            AnimalFactory.getInstance().putAnimal(animalList[i]);
            animalList[i] = null;
        }
        // stop background music
        GameLayout.getInstance().stopBackgroundClip();
        
        GameLayout.getInstance().getStartGameButton().setEnabled(true);
        
        //game over, tell the player
        JOptionPane.showMessageDialog(GameLayout.getInstance(), 
                GameLayout.getInstance().getGameStatus().getPlayerName() + ", your score is "
                    + Integer.toString(GameLayout.getInstance().getGameStatus().getScore()),
                    "Game Over !",
                    JOptionPane.INFORMATION_MESSAGE
                );
        // add to highscore
        //System.out.println("b");
        GameStatus gameStatus = GameLayout.getInstance().getGameStatus(); 
        gameStatus.addHighScore(gameStatus.getPlayerName(), gameStatus.getScore());
        System.out.println("Writing data..");
        System.out.println(gameStatus.highScore);
        
        gameStatus.writeHighScoretoFile();
        }
    }
    
}
