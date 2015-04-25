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
import javax.swing.JPanel;

public class AnimalFactory {
    Constructor animalConstructor;
    HashMap<String, ArrayList<Animal>> animalMap = new HashMap<>();
    ArrayList<Animal> listAnimal = new ArrayList<>();
    int id = 0,defaultt = 10;
    Field tempAnimalName;
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
   
        
    public void registerAnimal(Class animal) {
        try {
            animalConstructor = animal.getDeclaredConstructor(new Class[] {});
            Class c = Class.forName("Animal");
            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                if("animalName".equals(f.getName())) {
                    tempAnimalName =  f;
                }
            }
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

        animalMap.put(tempAnimalName.getName().toLowerCase(),listAnimal);
    }
    
    public Animal getAnimal() {
        // get all key of animalMap
        ArrayList<String> animalKey = new ArrayList<>();
        animalKey.addAll(animalMap.keySet());
        
        //get random key
        Random rand = new Random();
        int i = rand.nextInt(animalKey.size()); //random key index
        
        // get animal based on key
        ArrayList<Animal> animalList = animalMap.get(animalKey.get(i).toLowerCase());
        return animalList.remove(animalList.size()-1);
    }
    
    public void putAnimal(Animal animal) {
        listAnimal.add(animal);
        animalMap.put(tempAnimalName.getName().toLowerCase(),listAnimal);
    }
}
