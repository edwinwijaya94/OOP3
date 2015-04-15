import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public interface Animal {
    //attributes
    /*public int speed=0;
    public int size=0;
    public Point position= new Point(0,0);
    public EventObserver[] event_observer;
    public String word="";*/
    
    //method
    public void move();
    public void behaveWord();
    public void notifyObserver(EventObserver e);
}
