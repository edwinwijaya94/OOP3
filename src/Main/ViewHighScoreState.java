/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Jessica
 */
public class ViewHighScoreState implements State {
    @Override
    public void doAction() {
        HighScore score = HighScore.getInstance();
    }
}
