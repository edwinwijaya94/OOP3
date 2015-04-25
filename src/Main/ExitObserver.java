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
    @Override
    public void handle() {
        ArrayList<Animal> animalList = GameLayout.getInstance().getAnimals();
        
        for (int i = 0; i < (animalList.size()); i++) {
            animalList.get(i).getThread().interrupt();
        }
    }
}