import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elvan_owen
 */
public class GameFlowObserver implements EventObserver {
    private GameLayout gameLayout;
    private ArrayList<Integer> animalSpeed;


    @Override
    public void handlePauseGame() {
        ArrayList<Animal> animal = gameLayout.animal;
        for (int i=0;i<animal.size();i++)
        {
            animalSpeed.add(animal.get(i).getSpeed());
            animal.get(i).setSpeed(0);
        }
        gameLayout.gameFlow.pause();
    }
    
    @Override
    public void handleResumeGame() {
        ArrayList<Animal> animal = gameLayout.animal;
        for (int i=0;i<animal.size();i++)
        {
            animal.get(i).speed = animalSpeed.get(i);
        }
        gameLayout.gameFlow.resume();
    }

    @Override
    public void handleExitGame() {
        // no implementation
    }
}
