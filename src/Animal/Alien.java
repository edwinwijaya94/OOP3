package Animal;

import Main.AnimalFactory;
import Main.GameLayout;
import Main.Animal;
import Main.CaughtObserver;
import Main.EscapeObserver;
import Main.WordsDictionary;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elvan_owen
 */
public class Alien extends Animal {
    public static String animalName = "Alien";
    public String currentWord = "";
    
    //registering the class to AnimalFactory 
    static {
        AnimalFactory.getInstance().registerAnimal(Alien.class);
    }
    
    public void draw(int position) {
        currentWord = "";
        setSpeed(5);
        ImageIcon icon = new ImageIcon("image/alien.gif");
        Image image = icon.getImage();
        label = new JLabel();
        label.setText("");
        label.setIcon(icon);
        label.setSize(300,200);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        label.setForeground(Color.black);
        label.setFont(label.getFont().deriveFont((float)(label.getFont().getSize()+13)));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        int kanan = (int)GameLayout.getInstance().getPanel().getBounds().getMaxX();
        int atas = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getY() - 100;
        atas += position * label.getWidth()/2.6 + position*15 + 5 ;
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
                        //updatePosition();
                        long runningTime = (System.nanoTime() - startTime)/nsToms;
                        if(word.isEmpty()){
                            word = behaveWord(runningTime);
                            label.setText(word);
                        }
                        else if(runningTime >= changeWordDuration){ //encode chars
                            currentWord = behaveWord(runningTime);
                            startTime = System.nanoTime(); // reset the clock
                            label.setText(currentWord);
                        }
                        label.setLocation((int)label.getLocation().getX()-10, (int)label.getLocation().getY());
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
                    //GameLayout.getInstance().getPanel().remove(label);
                    GameLayout.getInstance().getPanel().revalidate();
                    GameLayout.getInstance().getPanel().repaint();
                    return;
                    //break;               
                }
            }
        };
        myThread.start();
    }                                               

    @Override
    public void playSound(){
        // Play music when answer corrected
        String path = "music/alien.wav";
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
    
    
    @Override
    public String behaveWord(long currentTime) {
        if (currentWord == "") {
            currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
            return currentWord;
        }
        else{ //encode chars, player must type real word
            return encodeChars(currentWord);
        }
    }
}
