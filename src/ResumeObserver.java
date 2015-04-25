
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vickonovianto
 */
public class ResumeObserver implements EventObserver {
    private static ResumeObserver instance;
    public static ResumeObserver getInstance()
    {
        if (instance == null){
            instance = new ResumeObserver();
        }
        return instance;
    }
    
    private ResumeObserver()
    {
    }
    
    @Override
    public void handle() {
        ArrayList<Animal> animal = GameLayout.getInstance().getAnimals();
        ArrayList<Integer> animalSpeed = PauseObserver.getInstance().getAnimalSpeed();
        for (int i=0;i<animal.size();i++)
        {
            animal.get(i).speed = animalSpeed.get(i);
        }
    }
}