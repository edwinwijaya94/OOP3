package Animal;

import Main.AnimalFactory;
import Main.GameLayout;
import Main.Animal;
import Main.CaughtObserver;
import Main.EscapeObserver;
import Main.WordsDictionary;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elvan_owen
 */
public class GreenDragon extends Animal {
    public static String animalName = "GreenDragon";
    public String currentWord = "";
    
// registering the class to AnimalFactory
    static {
        AnimalFactory.getInstance().registerAnimal(GreenDragon.class);
    }
    
    private void checkDeath()
    {
        
    }
    
    public void draw(int position) {
        currentWord = "";
        setSpeed(45);
        ImageIcon icon = new ImageIcon("image/greendragon.gif");
        Image image = icon.getImage();
        label = new JLabel();
        label.setText("");
        label.setIcon(icon);
        label.setSize(450,160);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        label.setForeground(Color.darkGray);
        label.setFont(label.getFont().deriveFont((float)(label.getFont().getSize()+13)));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        int kanan = (int)GameLayout.getInstance().getPanel().getBounds().getMaxX();
        int atas = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getY() - 100;
        atas += position * label.getWidth()/3.7 + position*15 + 10;
        label.setLocation(kanan,atas);
        label.setVisible(true);
        GameLayout.getInstance().getPanel().add(label, 0);
        move();
    }
    
    //method
    @Override
    public void move(){
         /*if (label == null) 
            draw();*/
         final long startTime = System.nanoTime();
         myThread = new Thread()  {
            public void run() {
                int kiri = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getX();
                try {
                    long startTime = System.nanoTime();
                    while(label.getLocation().getX() > kiri) {
                        long runningTime = System.nanoTime() - startTime;
                        if (word.isEmpty() || runningTime/1000000 >=changeWordDuration)
                        {
                            word = behaveWord(runningTime / 1000000);
                            startTime = System.nanoTime();
                        }
                        word = behaveWord(runningTime / 1000000);
                        label.setLocation((int)label.getLocation().getX()-10, (int)label.getLocation().getY());
                        label.setText(word);
                        GameLayout.getInstance().getPanel().revalidate();
                        GameLayout.getInstance().getPanel().repaint();
                        //Thread.sleep(100-speed);
                        delay(speed);
                    }
                    GameLayout.getInstance().getPanel().remove(label);
                    GameLayout.getInstance().getPanel().revalidate();
                    GameLayout.getInstance().getPanel().repaint();
                    EscapeObserver.getInstance().handle();
                    return;
                } catch (InterruptedException ex) {  
                    GameLayout.getInstance().getPanel().remove(label);
                    GameLayout.getInstance().getPanel().revalidate();
                    GameLayout.getInstance().getPanel().repaint();
                    return;
                    //break;               
                }
            }
        };
        myThread.start();
    }                                               

     private void updatePosition() {
        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {
               label.setLocation((int)label.getLocation().getX()-10, (int)label.getLocation().getY());
            }
        });
    }
 
    /*
    @Override
    public String behaveWord(long duration){
        if (currentWord == "") currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
        currentWord = currentWord.substring(1) + currentWord.charAt(0);
        return currentWord;
    }*/
}
