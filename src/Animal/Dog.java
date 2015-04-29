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
 * @author Edwin
 */
public class Dog extends Animal {
    public static String animalName = "Dog";
    public String currentWord = "";
    
// registering the class to AnimalFactory
    static {
        AnimalFactory.getInstance().registerAnimal(Dog.class);
    }
    
    public Dog()
    {
        setLive(2);
    }
    
    public void draw(int position) {
        currentWord = "";
        setSpeed(15);
        ImageIcon icon = new ImageIcon("image/dog.gif");
        Image image = icon.getImage();
        //image = image.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH); 
        //icon = new ImageIcon(image);
        label = new JLabel();
        label.setText("");
        label.setIcon(icon);
        label.setSize(250,160);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
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
    /*
    public void draw() {
        setSpeed(15);
        ImageIcon icon = new ImageIcon("image/dog.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(image);
        label = new JLabel();
        label.setText("");
        label.setIcon(icon);
        label.setSize(200,100);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label.setForeground(Color.GREEN);
        label.setFont(label.getFont().deriveFont((float)(label.getFont().getSize()+20)));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        int kanan = (int)GameLayout.getInstance().getPanel().getBounds().getMaxX() - label.getWidth() - 20;
        int atas = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getY() - 100;
        label.setLocation(kanan,atas);
        label.setVisible(true);
        GameLayout.getInstance().getPanel().add(label, BorderLayout.CENTER);
    }*/
    
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
                    while(label.getLocationOnScreen().getX() > kiri) {
                        //updatePosition();
                        long runningTime = (System.nanoTime() - startTime)/nsToms;
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
    
    /*@Override
    public String behaveWord(long currentTime) {
        
        if (currentWord == "") currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
            return currentWord;
        //GameLayout.getInstance().debug(WordsDictionary.getInstance().getWordsFromDictionary());
        //return "asem";
        //return WordsDictionary.getInstance().getWordsFromDictionary();
    }*/
}
