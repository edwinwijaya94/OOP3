package Main;

import java.io.FileInputStream;
import java.io.*;
import javax.sound.sampled.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public class TypeHandler extends Thread{
    
    private Thread thread;
    private String threadName = "TypeHandlerThread";
    private boolean pause = false;
    public Passer passer;
        
    public TypeHandler(Passer passer)
    {
        if (thread == null)
        {
            thread = new Thread (this, threadName);
            thread.start ();
            this.passer = passer;
        }
    }
    
    public void pause(){
        pause = true;
    }
    
    public void unpause(){
        pause = false;
    }
    
    public void run()
    {
        while (true){
            validateInput();
            while (pause) {
                try{
                    wait();
                }catch(Exception e){}
            }
        }
    }
    
    public void validateInput()
    {
        Animal[] animal = GameLayout.getInstance().getAnimals();
        for (int i=0;i<GameLayout.getInstance().getAnimalSize();i++)
        {
            if (passer.word.equals(animal[i].getWord()))
            {
                // Play music
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
                
                GameLayout.getInstance().addTotalCorrectWords();
                CaughtObserver.getInstance().setIndex(i);
                CaughtObserver.getInstance().handle();
                return;
            }
        }
        //GameLayout.getInstance().getStartGameButton().setEnabled(true);
        //GameLayout.getInstance().getStartGameButton().setText("Restart Game ?");
    }
}
