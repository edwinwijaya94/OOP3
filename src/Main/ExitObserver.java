package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jessica
 */
import java.util.*;

public class ExitObserver implements EventObserver {
    
    private static ExitObserver instance;
    
    public static ExitObserver getInstance()
    {
        if (instance == null){
            instance = new ExitObserver();
        }
        return instance;
    }
    
    private ExitObserver()
    {
    }
    
    
    @Override
    public void handle() {
        Animal[] animalList = GameLayout.getInstance().getAnimals();
        
        for (int i = 0; i < GameLayout.getInstance().getAnimalSize(); i++) {
            animalList[i].getThread().interrupt();
        }
    }
}