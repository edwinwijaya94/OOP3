package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edwin
 */
public class StartNewGameState implements State {

    @Override
    public void doAction() {
        GameLayout GL = GameLayout.getInstance();
        GL.setVisible(true);
    }
}
