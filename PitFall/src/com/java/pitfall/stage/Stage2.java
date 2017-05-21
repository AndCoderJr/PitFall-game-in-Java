package com.java.pitfall.stage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import com.java.pitfall.environment.utils.*;
import com.java.pitfall.constants.Constants;

public class Stage2 extends JFrame implements Constants{
	private Image water;
	private URL resource;
	private int[] xCorda = {490,490,490,490,490,490,490,490,490,490,490,490};
	private int[] yCorda = {175,180,200,220,240,260,280,300,320,340,360,380};
	private int contMove;
	private boolean rigth;
	private boolean left;
	public Stage2(){
		this.contMove = 2;
		this.rigth = true;
		this.left = false;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		resource = getClass().getResource("../environment/utils/agua.png");

		try {
			water = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(water, 250, 480,480,200, this);
		g.setColor(Color.black);
		for(int i = 0 ; i < xCorda.length; i++){
			g.fillRect(xCorda[i], yCorda[i], 20, 10);

		}
		
	}

	public void moveCorda(){
		if(rigth){
			for (int i = 0; i < xCorda.length; i++) {
				contMove += 2;
				xCorda[i] += (int) (i * (contMove)) * GRAVITY;
				if(contMove >= 18 ){
					this.rigth = false;
					this.left = true;
					this.contMove = 2;
				}
			}
			
		}else if(left){
			for (int i = 0 ; i < xCorda.length ; i++){
				contMove += 2;
				xCorda[i] -= (int) (i * (contMove)) * GRAVITY;
				if(contMove >= 18 ){
					this.left = false;
					this.rigth = true;
					this.contMove = 2;
				}
			} 
			
		}
	}

}

