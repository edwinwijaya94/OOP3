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
    // registering the class to AnimalFactory
    static{
        AnimalFactory.getInstance().registerAnimal("Cat",Cat.class);
    }
    
    public Cat(JPanel panel)
    {
       ImageIcon icon = new ImageIcon("image/cat.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(image);
        JLabel jLabel2 = new JLabel();
        jLabel2.setText("");
        jLabel2.setIcon(icon);
        jLabel2.setSize(200,100);
        jLabel2.setLocation(300,10);
        panel.add(jLabel2, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();   
        move();
    }
    
    //method
    @Override
    public void move(){
    
         Thread myThread = new Thread()  {
            public void run() {
                for(int i = 0; i < 50; i++) {
                    updateTest(i, jLabel2);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        
                    }
                }
            }
        };
        myThread.start();
    }                                               

     private void updateTest(int i, JLabel label) {
        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {
               label.setLocation((int)label.getLocation().getX()-10, (int)label.getLocation().getY());
            }
        });
    }
    }
    
    @Override
    public void behaveWord(){
    
    }
    
    @Override
    public void notifyObserver(EventObserver e){
        
    }
}
