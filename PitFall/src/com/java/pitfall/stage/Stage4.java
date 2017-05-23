package com.java.pitfall.stage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.java.pitfall.constants.Constants;

public class Stage4 extends JFrame implements Constants{
	private Image gold;
	private URL resource;
	public static int goldY;
	public Stage4(){
		this.goldY = 480;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		resource = getClass().getResource("../environment/utils/gold.png");

		try {
			gold = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(gold, 800, this.goldY,64,64, this);
		g.drawImage(gold, 850, this.goldY, this);
		g.drawImage(gold, 850, this.goldY - 10, this);
		g.drawImage(gold, 845, this.goldY - 10, this);
		
	}
	
	public Rectangle goldBounds(){
		return(new Rectangle(800, this.goldY,64,64));
	}

}
