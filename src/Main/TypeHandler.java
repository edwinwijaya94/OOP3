package Main;

import java.util.*;
import javax.swing.JTextField;
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
            //GameLayout.getInstance().debug(passer.word);
            //GameLayout.getInstance().debug("test");
            while (pause) {
                try{
                    wait();
                }catch(Exception e){}
            }
        }
    }
    
    public void validateInput()
    {
        ArrayList<Animal> animal = GameLayout.getInstance().getAnimals();
        for (int i=0;i<animal.size();i++)
        {
            //GameLayout.getInstance().debug(GameLayout.getInstance().getTextField().getText());
            if (passer.word.equals(animal.get(i).getWord()))
            {
                CaughtObserver.getInstance().setAnimal(animal.get(i));
                CaughtObserver.getInstance().handle();
                return;
            }
        }
        GameLayout.getInstance().getStartGameButton().setEnabled(true);
        GameLayout.getInstance().getStartGameButton().setText("Restart Game ?");
    }
}
