package com.java.pitfall.main;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import com.java.pitfall.constants.*; //Importa constantes
import com.java.pitfall.environment.Character; //Importa personagem
import com.java.pitfall.stage.*; //Importa fase

public class Screen extends JFrame implements Constants, Runnable, KeyListener{
	static boolean stage1;
	static boolean stage2;
	static boolean stage3;
	static boolean stage4;
	private Thread th1;
	private DefaultStage stage;
	static Stage1 stageOne;
	static Stage2 stageTwo;
	static Stage3 stageThree;
	static Stage4 stageFour;
	private Image img; //Concertar bug da tela piscando
	private Graphics gfx;
	static Character player;
	private Collisions collisions;
	public int points;
	public Screen() {

	}

	public void init() throws IOException, InterruptedException{
		this.setTitle("Pitfall");
		this.setSize(WIDTH_SCREEN,HEIGTH_SCREEN);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//Corrigir bug da tela piscando
		this.stage1 = false;
		this.stage2 = false;
		this.stage3 = false;
		this.stage4 = false;
		img =createImage(WIDTH_SCREEN, HEIGTH_SCREEN);
		this.points = 0;
		collisions = new Collisions();
		gfx = img.getGraphics();
		player = new Character();
		stage = new DefaultStage();
		stageOne = new Stage1();
		stageTwo = new Stage2();
		stageThree = new Stage3();
		stageFour = new Stage4();
		this.addKeyListener(this);
		th1 = new Thread(this);
		th1.start();
	}


	@Override
	public void paint(Graphics g) {
		super.paint(gfx);
		//desenha fase
		stage.paint(gfx); 
		player.paint(gfx);
		collisions.paint(gfx);
		if(stage1){
			player.paint(gfx);
			stageOne.paint(gfx);
		}else if(stage2){
			player.paint(gfx);
			stageTwo.paint(gfx);
		}else if(stage3){
			stageThree.paint(gfx);
			System.out.println("Fase 3 !!!");
		}else if(stage4){
			stageFour.paint(gfx);
		}




		g.drawImage(img, 0, 0, this);  //Corrigir bug da tela piscando


	}

	@Override
	public void run() {	//LEMBRAR DE FAZER UMA TELA DE INICIO
		while (true){
			repaint();
			player.move();
			player.setSprite();
			player.changeStage();
			checkStage();
			collisions.checkCollision();
			if(stageTwo.getChar())
				player.corda(true, true,stageTwo.getX());
			else
				player.corda(false, true,0);
			if(stage3)
				stageThree.moveFall();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}



	//Checar fase atual

	public void checkStage(){
		int stageNum = player.getStage();
		System.out.println("Fase " + stageNum);
		if(stageNum == 1)
			stage1 = true;
		else
			stage1 = false;

		if(stageNum == 2)
			stage2 = true;
		else
			stage2 = false;

		if(stageNum == 3)
			stage3 = true;

		else 
			stage3 = false;
		
		if(stageNum == 4)
			stage4 = true;
		
		else
			stage4 = false;

	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_RIGHT){
			player.setRigth(true);
		}

		if(e.getKeyCode() == e.VK_LEFT){
			player.setLeft(true);
		}

		if(e.getKeyCode() == e.VK_SPACE){
			player.setJump(true);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_RIGHT){
			player.setRigth(false);
		}
		if(e.getKeyCode() == e.VK_LEFT){
			player.setLeft(false);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
