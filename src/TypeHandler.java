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
public class TypeHandler extends Thread{
    
    private Thread thread;
    private String threadName = "TypeHandlerThread";
    private GameLayout gameLayout;
    private EventObserver eventHandler;
    private boolean pause = false;
    
    public TypeHandler(GameLayout gameLayout, EventObserver eventHandler)
    {
        if (thread == null)
        {
            this.gameLayout = gameLayout;
            this.eventHandler = eventHandler;
            thread = new Thread (this, threadName);
            thread.start ();
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
        ArrayList<Animal> animal = GameLayout.getInstance().getAnimals();
        for (int i=0;i<animal.size();i++)
        {
            if (GameLayout.getInstance().getTextField().getText().equals(animal.get(i).getWord()))
            {
                CaughtObserver.getInstance().setAnimal(animal.get(i));
                CaughtObserver.getInstance().handle();
                return;
            }
        }
    }
}
