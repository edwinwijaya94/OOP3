/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
import java.util.*;

public class AnimalFactory {
    private HashMap<Animal, Collection<Animal>> animalMap = new HashMap<>();
    Collection<Animal> listAnimal = new ArrayList<>();
    listAnimal = animalMap.get(Animal);
    
    void registerAnimal(Class animal) {
        Method m = animal.getMethod("setId", Integer.class);
        Sting[] params = null;
        m.invoke(null, id++);
    }
    
    animalMap.put(Animal, listAnimal);
    
    Integer id = 0;
    void setId (Animal animal) {
        
    }
    
    
    public void releaseAnimal(Animal) {
        
    }
   
    Integer getId() {
        return id;
    }
    
    public Animal getAnimal(Animal animal){ 
        AnimalMap.get(animal.getId());
    }
}