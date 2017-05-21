package com.java.pitfall.stage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;

import com.java.pitfall.constants.Constants;

public class Stage1 extends JFrame implements Constants {
	
	public Stage1() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		super.paint(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(400, 500, 150, 50);
	}
	
	
	
	
	public Rectangle buraco(){
		return (new Rectangle(400, 500, 120 - CONSTANTE_COLISAO_BURACO, 50));
	}
	
}
