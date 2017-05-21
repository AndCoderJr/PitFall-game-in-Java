package com.java.pitfall.stage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;

import com.java.pitfall.constants.Constants;

public class Stage1 extends JFrame implements Constants {
	private int barrilX;
	
	
	public Stage1() {
		this.barrilX = 800;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		//radio = 0;
		super.paint(g);
		Graphics2D gg = (Graphics2D) g;
		gg.setColor(Color.BLACK);
		gg.fillRect(400, 500, 150, 50); //Buraco
		gg.setColor(new Color(115,77,38));
		gg.fillRoundRect(barrilX, 520, 50, 50, 30, 40);
		
		gg.setColor(new Color(115,77,38));
		gg.fillRoundRect(barrilX + 250, 520, 50, 50, 30, 40);
		
		gg.setColor(Color.black);
		//gg.rotate(radio);
		
		
		barrilMove();
		
		
		
	}
	
	public void barrilMove(){
		this.barrilX -= 20;
		
		if(this.barrilX < -200)
			this.barrilX = 1040;
	}
	
	
	
	
	public Rectangle buraco(){
		return (new Rectangle(400, 500, 120 - CONSTANTE_COLISAO_BURACO, 50));
	}
	
	public Rectangle barril(){
		return (new Rectangle(barrilX + 200, 500, 30 , 50));
	}
	
	public Rectangle barril2(){
		return (new Rectangle(barrilX - 100, 500, 30, 50));
	}
	
}
