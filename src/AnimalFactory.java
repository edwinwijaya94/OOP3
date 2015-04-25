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
    public HashMap<String, ArrayList<Animal>> animalMap = new HashMap<>();
    public ArrayList<Animal> listAnimal = new ArrayList<>();
    int id = 0;
    int defaultt = 10;
    Field tempAnimalName;
    private static AnimalFactory instance;
    
    static
    {
        try{
            Class.forName("Cat");
        }catch(ClassNotFoundException e){
        }
    }
    
    private AnimalFactory()
    {}
    
    public static AnimalFactory getInstance()
    {
        if (instance == null)
            instance = new AnimalFactory();
        return instance;
    }
    
    public void registerAnimal(Class animal) {
        try {
            animalConstructor = animal.getDeclaredConstructor(new Class[] {});
            Field[] fields = animal.getDeclaredFields();
            for (Field f : fields) {
                if("animalName".equals(f.getName())) {
                    tempAnimalName =  f;
                    break;
                }
            }
            }catch(Exception e) {
                System.out.println(e);
            }
            
            for (int j=0;j<defaultt;j++)
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
    
    public ArrayList<String> animalKey = new ArrayList<>();
    public Animal getAnimal() {
        // get all key of animalMap
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
