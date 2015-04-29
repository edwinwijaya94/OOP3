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
    private int animalIndex;
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
    
    public void setIndex(int index)
    {
        this.animalIndex = index;
        animal = gameLayout.getAnimals()[index];
    }
    
    @Override
    public void handle() {
        /*
        gameLayout.getGameStatus().addScore(animal.getSpeed());
        gameLayout.getTextField().setText("");
        animal.getThread().interrupt();
        AnimalFactory.getInstance().putAnimal(animal);

        gameLayout.getAnimals()[animalIndex] = null;
        Animal tempAnimal = AnimalFactory.getInstance().getAnimal();
        gameLayout.getAnimals()[animalIndex] = tempAnimal;
        tempAnimal.draw(animalIndex);
        */
        
        
        int n = animal.getLive();
        //System.out.println(n);
        n--;
        animal.setLive(n);
        
        gameLayout.getGameStatus().addScore(animal.getSpeed());
        gameLayout.getTextField().setText("");
        
        if(animal.getLive()== 0){ //caught
            
            animal.getThread().interrupt();
            GameLayout.getInstance().getPanel().remove(animal.getLabel());
            GameLayout.getInstance().getPanel().revalidate();
            GameLayout.getInstance().getPanel().repaint();
            AnimalFactory.getInstance().putAnimal(animal);
            animal.setWord("");
            animal.setCurrentWord("");
   
            
            gameLayout.getAnimals()[animalIndex] = null;
            Animal tempAnimal = AnimalFactory.getInstance().getAnimal();
            gameLayout.getAnimals()[animalIndex] = tempAnimal;
            tempAnimal.draw(animalIndex);
        }
        else{ // still has live
            //animal.setCurrentWord(WordsDictionary.getInstance().getWordsFromDictionary());
            animal.setWord("");
            animal.setCurrentWord("");
        }

        //tempAnimal.move();
    }
}
