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

    private GameLayout gameLayout;
    private Animal animal;
    CaughtObserver(GameLayout gameLayout)
    {
        this.gameLayout = gameLayout;
    }
    
    @Override
    public void handle() {
        AnimalFactory.getInstance().putAnimal(animal);
        animal.label.setVisible(false);
        animal.getThread().interrupt();
        gameLayout.animal.remove(animal);
        gameLayout.animal.add(AnimalFactory.getInstance().getAnimal());        
        
    }
}
