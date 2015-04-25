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
    
    public void run()
    {
        while (true)
        {
            validateInput();
            while (gameLayout.gameFlow.isPause())
            {
                try{
                    wait();
                }catch(Exception e)
                {}
            }
        }
    }
    
    public void validateInput()
    {
        ArrayList<Animal> animal = gameLayout.animal;
        for (int i=0;i<animal.size();i++)
        {
            if (textbox.text.equals(animal.get(i).word))
            {
                eventHandler.handleCorrectTyping(animal.get(i));
                return;
            }
        }
    }
}
