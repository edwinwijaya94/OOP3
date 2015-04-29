/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jessica
 */

package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GameLayout;

public class Background {
	private static final int Height = 560;
	private static final int Width = 1355;
	int x = 13;
	int y = 74;
	private GameLayout gameLayout;

	BufferedImage image = null;{
		try {
		    image = ImageIO.read(new File("image/gamelayout.png"));
		} catch (IOException e) {
		}}
	
	public Background(GameLayout game) {
		this.gameLayout = gameLayout;
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(image,x,y,Width,Height, gameLayout);
	}
}


