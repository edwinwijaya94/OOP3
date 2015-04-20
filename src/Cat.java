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
    public int getSpeed(){
        return speed;
    }
    public int getSize(){
        return size;
    }
    public Point getPosition(){
        return position;
    }
    public EventObserver[] getEventObserver(){
        return event_observer;
    }
    public String getWord(){
        return word;
    }
    
    //method
    public void move(){
    
    }
    public void behaveWord(){
    
    }
    public void notifyObserver(EventObserver e){
        
    }
}
