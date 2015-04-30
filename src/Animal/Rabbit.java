package Animal;

import Main.AnimalFactory;
import Main.GameLayout;
import Main.Animal;
import Main.EscapeObserver;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


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
    
    @Override
    public void draw(int position) {
        currentWord = "";
        setSpeed(20);
        ImageIcon icon = new ImageIcon("image/rabbit.gif");
        label = new JLabel();
        label.setText("");
        label.setIcon(icon);
        label.setSize(250,160);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label.setForeground(Color.black);
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
         final long startTime = System.nanoTime();
         myThread = new Thread()  {
            @Override
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
                        label.setLocation((int)label.getLocation().getX()-10, (int)label.getLocation().getY());
                        label.setText(word);
                        GameLayout.getInstance().getPanel().revalidate();
                        GameLayout.getInstance().getPanel().repaint();
                        delay(speed);
                    }
                    GameLayout.getInstance().getPanel().remove(label);
                    GameLayout.getInstance().getPanel().revalidate();
                    GameLayout.getInstance().getPanel().repaint();
                    EscapeObserver.getInstance().handle();
                } catch (InterruptedException ex) {               
                }
            }
        };
        myThread.start();
    }                                               

    @Override
    public void playSound(){
        // Play music when answer corrected
        String path = "music/rabbit.wav";
        try{
            File audioFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);            
            audioClip.start();
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    } 
}
