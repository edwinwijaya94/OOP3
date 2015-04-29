package Main;



import java.awt.Point;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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
    
    //constant
    public static final long nsToms = 1000000;
    
    //attributes
    public static String animalName;
    protected int defaultLive = 1;
    protected JLabel label;
    public Thread myThread;
    public int speed = 0;
    public int size=0;
    public Point position= new Point();
    public EventObserver[] eventObserver;
    public String word="";
    public int live = 1;
    
    public long changeWordDuration = 3000;
    public String currentWord="";
    
    //getter, setter
    public final int getDefaultLive() {
        return defaultLive;
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
    public String getCurrentWord(){
        return currentWord;
    }
    public void setCurrentWord(String s){
        currentWord = s;
    }
    public JLabel getLabel() {
        return label;
    }
    
    public void delay(int animalSpeed) throws InterruptedException
    {
        int fastestSpeed = 30;
        int maximumAnimalSpeed = 100;
        int duration = maximumAnimalSpeed - animalSpeed - GameLayout.getInstance().getTotalCorrectWords()/7;
        duration = duration > fastestSpeed ? duration : fastestSpeed;
        Thread.sleep(duration);
    }
    
    // default sound for animal
    public void playSound(){
        // Play music when answer corrected
        String path = "music/yes.wav";
        try{
            File audioFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);            
            audioClip.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    //abstract method
    //public abstract void draw();
    public abstract void draw(int position);
    public abstract void move();
    
    public final String behaveWordDefault(){
        if (currentWord == "") 
            currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
        //currentWord = currentWord.substring(1) + currentWord.charAt(0);
        return currentWord;
    }
    
    public String behaveWord(long currentTime){
        return behaveWordDefault();
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
    
    public final String encodeChars(String currentWord){
        String temp = currentWord;
        for(int i = 0; i < temp.length(); i++){
            switch(temp.charAt(i)){
                case 'a':{
                    if(i == 0)
                            temp = "4" + temp.substring(i+1);
                    else if(i == temp.length())
                            temp = temp.substring(0,i-1)+ "4";
                    else
                            temp = temp.substring(0,i) + "4" + temp.substring(i+1);
                    break;
                }
                case 'e':{
                    if(i == 0)
                            temp = "3" + temp.substring(i+1);
                    else if(i == temp.length())
                            temp = temp.substring(0,i-1)+ "3";
                    else
                            temp = temp.substring(0,i) + "3" + temp.substring(i+1);
                    break;
                }
                case 'i':{
                    if(i == 0)
                            temp = "1" + temp.substring(i+1);
                    else if(i == temp.length())
                            temp = temp.substring(0,i-1)+ "1";
                    else
                            temp = temp.substring(0,i) + "1" + temp.substring(i+1);
                    break;
                }
                case 'j':{
                    if(i == 0)
                            temp = "7" + temp.substring(i+1);
                    else if(i == temp.length())
                            temp = temp.substring(0,i-1)+ "7";
                    else
                            temp = temp.substring(0,i) + "7" + temp.substring(i+1);
                    break;
                }
                case 's':{
                    if(i == 0)
                            temp = "5" + temp.substring(i+1);
                    else if(i == temp.length())
                            temp = temp.substring(0,i-1)+ "5";
                    else
                            temp = temp.substring(0,i) + "5" + temp.substring(i+1);
                    break;
                }
                case 'z':{
                    if(i == 0)
                            temp = "2" + temp.substring(i+1);
                    else if(i == temp.length())
                            temp = temp.substring(0,i-1)+ "2";
                    else
                            temp = temp.substring(0,i) + "2" + temp.substring(i+1);
                    break;
                }
            }
        }
        return temp;
    }
}
