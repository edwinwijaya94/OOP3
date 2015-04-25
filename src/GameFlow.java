/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elvan_owen
 */
public class GameFlow {
    private boolean pause = false;
    public boolean isPause()
    {
        return pause;
    }
    public void pause()
    {
        pause = true;
    }
    public void resume()
    {
        pause = false;
    }
}
