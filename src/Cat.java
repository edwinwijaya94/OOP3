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
public class Cat implements Animal {
    //attributes
    public int speed=0;
    public int size=0;
    public Point position= new Point();
    public EventObserver[] event_observer;
    public String word="";
    
    //getter
    @Override
    public int getSpeed(){
        return speed;
    }
    
    @Override
    public int getSize(){
        return size;
    }
    
    @Override
    public Point getPosition(){
        return position;
    }
    
    @Override
    public EventObserver[] getEventObserver(){
        return event_observer;
    }
    
    @Override
    public String getWord(){
        return word;
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
