package com.java.pitfall.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.java.pitfall.constants.Constants;
import com.java.pitfall.environment.utils.Sound;

public class Character extends JFrame implements Constants {

	private boolean isMoveLeft;
	private boolean left;
	private boolean isMoveRigth;
	private boolean rigth;
	private boolean isStop;
	private boolean jump;
	private boolean fall;
	private String path;
	private Image image;
	private int numJump;
	public int x;
	public int y;
	private int heigth;
	private int width;
	private URL resource;
	private int contRigth;
	private int contStage;
	private int initX;
	private int spriteWidth;
	private int spriteHeigth;
	private Sound song;
	private int sound;
	public Character() throws IOException{
		song = new Sound();
		this.rigth = true;
		this.left = false;
		this.x = 100;
		this.y = 350;
		this.spriteWidth = 256;
		this.spriteHeigth = 512;
		this.contStage = 0;
		this.initX = x;
		this.isStop = true;
		this.isMoveLeft = false;
		this.isMoveRigth = false;
		this.jump = false;
		this.fall = false;
		this.sound = 0;
		setSprite();
	}


	@Override
	public void paint(Graphics g)  {
		super.paint(g);
		resource = getClass().getResource(this.path);
		try {
			image = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawString("X - " + this.x, 20, 300);
		g.drawString("Y - " + this.y,300, 300);
		g.drawImage(image, this.x, this.y,spriteWidth,this.spriteHeigth, this);

	}


	public void setSprite(){
		if(isStop && !isMoveRigth && !isMoveLeft){
			if(rigth)	{
				path = "utils/SpriteStop.png";
				
			}
			else if(left){
				this.spriteWidth = 500;
				this.spriteHeigth = 512;
				path = "utils/left.png";
			}
				
				
		}

		if(isMoveLeft){
			rigth = false;
			left = true;
		}

		if(isMoveRigth){
			rigth = true;
			left = false;
		}
	}


	public void move(){
		if(fall){
			System.out.println("Caiu!");
			this.y +=(int) 20 * GRAVITY;
		}
		if(isMoveRigth){
			if(contRigth == 0){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/runRigth.png";
				this.x += 40;
				contRigth++;
			}else if(contRigth > 0){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/SpriteStop.png";
				this.x += 40;
				contRigth--;
			}

		}
		if(isMoveLeft){
			if(contRigth == 0){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/runLeft.png";
				this.x -= 40;
				contRigth++;
			}else if(contRigth > 0){
				this.spriteWidth = 500;
				this.spriteHeigth = 512;
				this.path = "utils/left.png";
				this.x -= 40;
				contRigth--;
			}
		}
		if (jump){
			if(sound == 0){
				song.song("/home/bigboss/jump.wav");
				sound++;
			}
			if(isMoveLeft){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/runLeft.png";
				contRigth = 4;
				
			}

			else if(isMoveRigth){
				
				this.path = "utils/runRigth.png";
				contRigth = 4;
			}


			if(numJump > 120){
				
				this.y += (int) 40 * GRAVITY;
				this.numJump += (int) 40 * GRAVITY;
				if(numJump > 240){
					this.spriteWidth = 256;
					this.spriteHeigth = 512;
					this.numJump = 0;
					this.jump = false;
					this.y = 350;
					contRigth = 0;
					sound = 0;
				}
			}else{
				this.y -= (int) 40 * GRAVITY;
				this.numJump += (int) 40 * GRAVITY;
				
			}
		}
		
		
	}
	
	public void changeStage(){
		if(this.x > 860){
			this.contStage++;
			this.x = this.initX - 20;
		}if (this.x < -100){
			this.contStage--;
			this.x = 850;
		}
	}

	public int getStage(){
		
		return contStage;
	}

	public void setRigth(boolean rigth){
		this.isMoveRigth = rigth;


	}

	public void setLeft(boolean left){
		this.isMoveLeft = left;

	}

	public void setJump(boolean jump){
		this.jump = true;
	}
	
	public void setFall(boolean fall){
		
		this.fall = jump;
		
	}
	
	public Rectangle playerDim(){
		if(left){
			return (new Rectangle( this.x, this.y,30 ,this.spriteHeigth - 300));
		}else{
			return (new Rectangle( this.x, this.y,this.spriteWidth - 200,this.spriteHeigth - 350));
		}
		
		
	}
}
