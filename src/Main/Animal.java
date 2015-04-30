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
import java.util.*;
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
    
    public static int speedLevel = 0;
    
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
    
    public static int getSpeedLevel(){
        return speedLevel;
    }
    public static void setSpeedLevel(int n){
        speedLevel = n;
    }
    
    public void delay(int animalSpeed) throws InterruptedException
    {
        int fastestSpeed = 30;
        int maximumAnimalSpeed = 100;
        int duration = maximumAnimalSpeed - animalSpeed - GameLayout.getInstance().getTotalCorrectWords()/7;
        duration -= speedLevel;
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
    public abstract void draw(int position);
    public abstract void move();
    
    public final String behaveWordDefault(){
        if (currentWord == "") 
            currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
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
        HashMap<String, String> charsTable = new HashMap<>();
		charsTable.put("a","4");
		charsTable.put("e","3");
		charsTable.put("i","1");
		charsTable.put("j","7");
		charsTable.put("o","0");
		charsTable.put("s","5");
		charsTable.put("z","2");
		for(int i = 0; i < temp.length(); i++){
			String encoded = charsTable.get(temp.substring(i,i+1));
			if(encoded != null){
				if(i == 0)
					temp = encoded + temp.substring(i+1);
				else if(i == temp.length())
					temp = temp.substring(0,i-1)+ encoded;
				else
					temp = temp.substring(0,i) + encoded + temp.substring(i+1);	
			}	
        }
        return temp;
    }
}
