package Animal;

import Main.AnimalFactory;
import Main.GameLayout;
import Main.Animal;
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
public class Rabbit extends Animal{
    public static String animalName = "Rabbit";
    public String currentWord = "";
    
// registering the class to AnimalFactory
    static {
        AnimalFactory.getInstance().registerAnimal(Rabbit.class);
    }
    
    public Rabbit()
    {

    }
    
    public void draw() {
        setSpeed(20);
        ImageIcon icon = new ImageIcon("image/rabbit.png");
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
    }
    
    //method
    @Override
    public void move(){
         if (label == null) 
            draw();
         final long startTime = System.nanoTime();
         myThread = new Thread()  {
            public void run() {
                int kiri = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getX();
                try {
                    while(label.getLocationOnScreen().getX() > kiri) {
                        updatePosition();
                        long runningTime = System.nanoTime() - startTime;
                        word = behaveWord(runningTime / 1000000);
                        label.setText(word);
                        Thread.sleep(100-speed);
                    }
                } catch (InterruptedException ex) {  
                    label.setVisible(false);
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
    
    @Override
    public String behaveWord(long currentTime) {
        if (currentWord == "") currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
        return currentWord;
        //GameLayout.getInstance().debug(WordsDictionary.getInstance().getWordsFromDictionary());
        //return "asem";
        //return WordsDictionary.getInstance().getWordsFromDictionary();
    }
}
