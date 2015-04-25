import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public abstract class Animal {
    
    //attributes
    protected JLabel label;
    public Thread myThread;
    public int speed=0;
    public int size=0;
    public Point position= new Point();
    public EventObserver[] eventObserver;
    public String word="";
    
    
    //getter, setter
    public static final int getId(){
        return id;
    }
    
    public static final void setId(int ID){
        id = ID;
    }
    
    public final int getSpeed(){
        return speed;
    }
    public final int getSize(){
        return size;
    }
    public  final Point getPosition(){
        return position;
    }
    public  final EventObserver[] getEventObserver(){
        return eventObserver;
    }
    public  final String getWord(){
        return word;
    }
    public final String setSpeed(int speed) {
        this.speed = speed; 
    }
    public final Thread getThread() {
        return myThread;
    }
    
    //abstract method
    public abstract void move();
    public abstract String behaveWord(long currentTime);
    public abstract void notifyObserver(EventObserver e);
}
