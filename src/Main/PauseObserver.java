package Main;

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
public class PauseObserver implements EventObserver {
    private ArrayList<Integer> animalSpeed;
    private static PauseObserver instance;
    
    public static PauseObserver getInstance()
    {
        if (instance == null){
            instance = new PauseObserver();
        }
        return instance;
    }
    
    private PauseObserver()
    {
    }
    
    public ArrayList<Integer> getAnimalSpeed()
    {
        return animalSpeed;
    }
    
    @Override
    public void handle() {
        Animal[] animal = GameLayout.getInstance().getAnimals();
        for (int i=0;i<GameLayout.getInstance().getAnimalSize();i++)
        {
            animalSpeed.add(animal[i].getSpeed());
            animal[i].setSpeed(0);
        }
    }
}
