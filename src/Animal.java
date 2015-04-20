import java.util.ArrayList;
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
public interface Animal {
    
    //getter
    public int getSpeed();
    public int getSize();
    public Point getPosition();
    public EventObserver[] getEventObserver();
    public String getWord();
    
    //method
    public void move();
    public void behaveWord();
    public void notifyObserver(EventObserver e);
}
