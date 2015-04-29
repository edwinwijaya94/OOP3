package Main;



import java.awt.Point;
import java.util.Random;
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
    public int live = 1;
    
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
    public  final void setWord(String w){
        this.word = w;
    }
    public final void setSpeed(int speed) {
        this.speed = speed; 
    }
    public final Thread getThread() {
        return myThread;
    }
    public final int getLive(){
        return live;
    }
    public final void setLive(int n){
        live = n;
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
        currentWord = currentWord.substring(1) + currentWord.charAt(0);
        return currentWord;
    }
    
    // method library for use in behaveWord()
    public final String randomizeChars(String currentWord){
        String temp = currentWord;
        String newWord ="";
        int n = temp.length();
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            int r = rand.nextInt(temp.length());
            newWord += temp.charAt(r);
            if(i == n-1)
                    temp="";
            else{
                    if(r == 0)
                            temp = temp.substring(r+1);
                    else if(r == temp.length())
                            temp = temp.substring(0,r);
                    else
                            temp = temp.substring(0,r) + temp.substring(r+1);	
            }
        }
        return newWord;
    }
}
