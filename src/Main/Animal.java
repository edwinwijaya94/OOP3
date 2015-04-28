package Main;



import java.awt.Point;
import javax.swing.JLabel;
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
    public static String animalName;
    protected JLabel label;
    public Thread myThread;
    public int speed = 0;
    public int size=0;
    public Point position= new Point();
    public EventObserver[] eventObserver;
    public String word="";
    public long changeWordDuration = 8000;
    public String currentWord="";
    //getter, setter
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
    public final void setSpeed(int speed) {
        this.speed = speed; 
    }
    public final Thread getThread() {
        return myThread;
    }
    public void delay(int animalSpeed) throws InterruptedException
    {
        int fastestSpeed = 30;
        int maximumAnimalSpeed = 100;
        int duration = maximumAnimalSpeed - animalSpeed - GameLayout.getInstance().getTotalCorrectWords()/7;
        duration = duration > fastestSpeed ? duration : fastestSpeed;
        Thread.sleep(duration);
    }
    
    //abstract method
    //public abstract void draw();
    public abstract void draw(int position);
    public abstract void move();
    public String behaveWord(long currentTime){
        if (currentWord == "") currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
        else currentWord = currentWord.substring(1) + currentWord.charAt(0);
        return currentWord;
    }
}
