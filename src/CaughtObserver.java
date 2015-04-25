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
    CaughtObserver(GameLayout gameLayout)
    {
        this.gameLayout = gameLayout;
    }
    
    @Override
    public void handleCorrectTyping(Animal animal) {
        AnimalFactory.getInstance().returnAnimal(animal);
        gameLayout.animal.remove(animal);
    }

    @Override
    public void handleAnimalEscape() {
        // no implementation
    }

    @Override
    public void handlePauseGame() {
        // no implementation
    }

    @Override
    public void handleExitGame() {
        // no implementation
    }
    
}
