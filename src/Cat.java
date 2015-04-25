import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class Cat extends Animal {
    public static String animalName = "Cat";
    public String currentWord = "";
    
// registering the class to AnimalFactory
    static {
        AnimalFactory.getInstance().registerAnimal(Cat.class);
    }
    
    public Cat()
    {
        ImageIcon icon = new ImageIcon("image/cat.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(image);
        label = new JLabel();
        label.setText("");
        label.setIcon(icon);
        label.setSize(200,100);
        int kanan = (int)GameLayout.getInstance().getPanel().getBounds().getMaxX() - label.getWidth() - 20;
        int atas = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getY() - 100;
        label.setLocation(kanan,atas);
        label.setVisible(true);
    }
    
    //method
    @Override
    public void move(){
         GameLayout.getInstance().getPanel().add(label, BorderLayout.CENTER);
         GameLayout.getInstance().getPanel().revalidate();
         GameLayout.getInstance().getPanel().repaint();
         final long startTime = System.nanoTime();
         myThread = new Thread()  {
            public void run() {
                int kiri = (int)GameLayout.getInstance().getPanel().getLocationOnScreen().getX();
                while(label.getLocationOnScreen().getX() > kiri) {
                    try {
                        updatePosition();
                        long runningTime = System.nanoTime() - startTime;
                        word = behaveWord(runningTime / 1000000);
                        label.setText(word);
                        Thread.sleep(100-speed);
                    } catch (InterruptedException ex) {     
                    }
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
        //if (currentWord == "") currentWord = WordsDictionary.getInstance().getWordsFromDictionary();
        //return currentWord;
        return "asem";
    }
}
