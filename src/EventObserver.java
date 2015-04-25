/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public abstract class EventObserver {
   protected GameLayout gameLayout;
   public abstract void handleCorrectTyping();
   public abstract void handleAnimalEscape();
   public abstract void handlePauseGame();
   public abstract void handleExitGame();
}
