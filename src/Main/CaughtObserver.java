package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public class CaughtObserver implements EventObserver {

    private GameLayout gameLayout = GameLayout.getInstance();
    private Animal animal;
    
    private static CaughtObserver instance;
    
    public static CaughtObserver getInstance()
    {
        if (instance == null){
            instance = new CaughtObserver();
        }
        return instance;
    }
    
    private CaughtObserver()
    {
    }
    
    public void setAnimal(Animal animal)
    {
        this.animal = animal;
    }
    
    @Override
    public void handle() {
        AnimalFactory.getInstance().putAnimal(animal);
        animal.label.setVisible(false);
        animal.getThread().interrupt();
        gameLayout.getAnimals().remove(animal);
        //gameLayout.getAnimals().add(AnimalFactory.getInstance().getAnimal());        
        
    }
}
