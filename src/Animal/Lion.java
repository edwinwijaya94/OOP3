/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animal;

import Main.Animal;
import Main.AnimalFactory;
import Main.EscapeObserver;
import Main.GameLayout;
import Main.WordsDictionary;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jessica
 */

public class Lion extends Animal {
    public static String animalName = "Lion";
    public String currentWord = "";
    
// registering the class to AnimalFactory
    static {
        AnimalFactory.getInstance().registerAnimal(Lion.class);
    }
    
    public Lion()
    {

    }
    
    private void checkDeath()
    {
        
    }
    
    public void draw(int position) {
        currentWord = "";
        setSpeed(5);
        ImageIcon icon = new ImageIcon("image/lion.gif");
        Image image = icon.getImage();
        image = image.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH); 
        //icon = new ImageIcon(image);
        label = new JLabel();
        label.setText("");
        label.setIcon(icon);
        label.setSize(250,160);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label.setForeground(Color.GREEN);
        label.setFont(label.getFont().deriveFont((float)(label.getFont().getSize()+20)));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        int kanan = (int)GameLayout.getInstance().getPanel().getBounds().getMaxX();
        int atas = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getY() - 100;
        atas += position * label.getWidth()/2 + position*15 ;
        label.setLocation(kanan,atas);
        label.setVisible(true);
        //GameLayout.getInstance().getPanel().add(label, BorderLayout.CENTER);
        GameLayout.getInstance().getPanel().add(label);
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
                    while(label.getLocationOnScreen().getX() > kiri) {
                        long runningTime = System.nanoTime() - startTime;
                        if (word.isEmpty() || runningTime/1000000 >=changeWordDuration)
                        {
                            word = behaveWord(runningTime / 1000000);
                            startTime = System.nanoTime();
                        }
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
                    //label.setVisible(false);
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
    
    /*@Override
    public String behaveWord(long currentTime) {
        if (currentWord == "") currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
        return currentWord;
        //GameLayout.getInstance().debug(WordsDictionary.getInstance().getWordsFromDictionary());
        //return "asem";
        //return WordsDictionary.getInstance().getWordsFromDictionary();
    }*/
}