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
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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
        GameLayout.getInstance().getPanel().add(label, 0);
        move();
    }
    
    
    //method
    @Override
    public void move(){
         myThread = new Thread()  {
            public void run() {
                int kiri = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getX();
                try {
                    long startTime = System.nanoTime();
                    while(label.getLocation().getX() > kiri) {
                        
                        long runningTime = (System.nanoTime() - startTime)/nsToms;
                        if(word.isEmpty() || runningTime >= changeWordDuration){
                            word = behaveWord(runningTime);
                        }
                        label.setText(word);
                        label.setLocation((int)label.getLocation().getX()-10, (int)label.getLocation().getY());
                        GameLayout.getInstance().getPanel().revalidate();
                        GameLayout.getInstance().getPanel().repaint();
                        
                        delay(speed);
                    }
                    GameLayout.getInstance().getPanel().remove(label);
                    GameLayout.getInstance().getPanel().revalidate();
                    GameLayout.getInstance().getPanel().repaint();
                    EscapeObserver.getInstance().handle();
                    return;
                } catch (InterruptedException ex) {  
                    
                    GameLayout.getInstance().getPanel().revalidate();
                    GameLayout.getInstance().getPanel().repaint();
                    return;
                  
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
    public void playSound(){
        // Play music when answer corrected
        String path = "music/dog.wav";
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
    } 
     
     
    
}
