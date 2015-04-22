/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
import java.lang.reflect.*;
import java.util.*;

public class AnimalFactory {
    Constructor animalConstructor;
    HashMap<String, ArrayList<Animal>> animalMap = new HashMap<>();
    int id = 0,defaultt = 10;
    private static AnimalFactory instance;
    
    private AnimalFactory()
    {
		
    }
    public static AnimalFactory getInstance()
    {
        if (instance == null)
            instance = new AnimalFactory();
        return instance;
    }
        
    public void registerAnimal(String animalName, Class animal) {
        ArrayList<Animal> listAnimal = new ArrayList<>();
        try {
            animalConstructor = animal.getDeclaredConstructor(new Class[] {});
        }catch(Exception e) {
            System.out.println(e);
        }

        for (int i=0;i<defaultt;i++)
        {
            try {
                Animal tempAnimal = (Animal)animalConstructor.newInstance(new Object[] {});
                listAnimal.add(tempAnimal);
            }catch(Exception e) {
                System.out.println(e);
            }
        }

        animalMap.put(animalName.toLowerCase(),listAnimal);
    }
    
    public Animal getAnimal(String animalName) {
        ArrayList<Animal> animalList = animalMap.get(animalName.toLowerCase());
        return animalList.remove(animalList.size()-1);
    }
}