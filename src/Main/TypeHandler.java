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
    
    //private Thread thread;
    private String threadName = "TypeHandlerThread";
    private boolean pause = false;
    public Passer passer;
        
    public TypeHandler(Passer passer)
    {

        this.passer = passer;
        start();
    }
    
    
    public void run()
    {
        try {
            while(true) {
                validateInput();
                Thread.sleep(1);
            }
        } catch (InterruptedException e) { 
            System.out.println(e);
        }
    }
    
    public void validateInput()
    {
        Animal[] animal = GameLayout.getInstance().getAnimals();
        for (int i=0;i<GameLayout.getInstance().getAnimalSize();i++)
        {
            if (passer.word.equals(animal[i].getWord()))
            {
                animal[i].playSound();
                
                GameLayout.getInstance().addTotalCorrectWords();
                CaughtObserver.getInstance().setIndex(i);
                CaughtObserver.getInstance().handle();
                return;
            }
        }
    }
}
