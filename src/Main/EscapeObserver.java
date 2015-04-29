/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;

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
    public void handle() {
        Animal[] animalList = GameLayout.getInstance().getAnimals();
        
        for (int i = 0; i < GameLayout.getInstance().getAnimalSize(); i++) {
            animalList[i].getThread().interrupt();
            animalList[i] = null;
        }
        // stop background music
        GameLayout.getInstance().stopBackgroundClip();
        
        // add to highscore
        GameStatus gameStatus = GameLayout.getInstance().getGameStatus(); 
        gameStatus.addHighScore(gameStatus.getPlayerName(), gameStatus.getScore());
    }
}
