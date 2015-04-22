import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public class Cat extends Animal {
    // registering the class to AnimalFactory
    static{
        AnimalFactory.getInstance().register(Cat.class);
    }
    
    //method
    @Override
    public void move(){
    
    }
    
    @Override
    public void behaveWord(){
    
    }
    
    @Override
    public void notifyObserver(EventObserver e){
        
    }
}
