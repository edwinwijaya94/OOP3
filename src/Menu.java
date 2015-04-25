/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public class Menu extends javax.swing.JFrame {
    
    // attributes
    private State menuState;
    
    //methods
    public void setMenuState(State state){
        this.menuState= state;
    }
    
    public State getMenuState(){
        return this.menuState;
    }
    
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        menuState=null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        startNewGameButton = new javax.swing.JButton();
        viewHighScoreButton = new javax.swing.JButton();
        howToPlayButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        exitGameButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BackgroundMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        startNewGameButton.setText("Start New Game");
        startNewGameButton.setName(""); // NOI18N
        startNewGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startNewGameButtonActionPerformed(evt);
            }
        });
        jPanel1.add(startNewGameButton);
        startNewGameButton.setBounds(360, 190, 120, 23);
        startNewGameButton.getAccessibleContext().setAccessibleDescription("");

        viewHighScoreButton.setText("View High Scores");
        jPanel1.add(viewHighScoreButton);
        viewHighScoreButton.setBounds(360, 240, 120, 23);

        howToPlayButton.setText("How to Play");
        jPanel1.add(howToPlayButton);
        howToPlayButton.setBounds(360, 290, 120, 23);

        aboutButton.setText("About");
        jPanel1.add(aboutButton);
        aboutButton.setBounds(360, 340, 120, 23);

        exitGameButton.setText("Exit Game");
        jPanel1.add(exitGameButton);
        exitGameButton.setBounds(360, 390, 120, 23);

        jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 61, 79));
        jLabel1.setText("TYPER ANIMAL");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(300, 110, 250, 50);

        BackgroundMenu.setIcon(new javax.swing.ImageIcon("D:\\Users\\Jessica\\Pictures\\Background\\menu2.png")); // NOI18N
        jPanel1.add(BackgroundMenu);
        BackgroundMenu.setBounds(0, 0, 800, 510);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startNewGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startNewGameButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startNewGameButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundMenu;
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton exitGameButton;
    private javax.swing.JButton howToPlayButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton startNewGameButton;
    private javax.swing.JButton viewHighScoreButton;
    // End of variables declaration//GEN-END:variables
}
