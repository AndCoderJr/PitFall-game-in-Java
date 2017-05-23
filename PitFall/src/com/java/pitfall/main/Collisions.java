package com.java.pitfall.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.java.pitfall.constants.Constants;
import com.java.pitfall.environment.utils.Sound;
import com.java.pitfall.stage.Stage1;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Collisions extends Screen implements Constants{
	private Sound song;
	private int cont;
	private int deathCont;
	public Collisions() throws IOException, InterruptedException {
		//super();
		song = new Sound();
		this.deathCont = 0;

	}

	public void checkCollision(){
		Rectangle fall =   this.stageOne.buraco();
		Rectangle p1   =   this.player.playerDim();
		Rectangle barril1 = this.stageOne.barril();
		Rectangle barril2 = this.stageOne.barril2();
		Rectangle cordaStage2 = this.stageTwo.cordaBounds();
		Rectangle agua1 = this.stageTwo.waterBounds();
		Rectangle fall2 = this.stageThree.fallBounds();
		Rectangle gold = this.stageFour.goldBounds();
		if(stage1){
			if(barril1.intersects(p1)){
				this.cont++;
			}

			if(barril2.intersects(p1)){
				this.cont++;
			}

			if(fall.intersects(p1)){
				this.cont++;
				song.song("/home/bigboss/death.wav");
				this.deathCont++;
				//player.y += 10;
				player.y = 580;

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}else if (stage2){
			if(cordaStage2.intersects(p1)){
				stageTwo.setChar(true);
				song.song("/home/bigboss/corda.wav");
				player.corda(true,true, stageTwo.getX());
			}else if(agua1.intersects(p1)){
				System.out.println("Colidiu agua");


				song.song("/home/bigboss/death.wav");
				this.deathCont++;
				//player.y += 10;
				player.y = 1000000;
				player.spriteHeigth -= 20;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}else if(stage3){
			if(fall2.intersects(p1)){
				System.out.println("Colidiu agua");
				song.song("/home/bigboss/death.wav");
				this.deathCont++;
				//player.y += 10;
				player.y = 1000000;
				player.spriteHeigth -= 20;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(stage4){
			if(gold.intersects(p1)){
				song.song("/home/bigboss/Super Mario Bros.-Coin Sound Effect-RfkcI8dhfsQ.wav");
				stageFour.goldY = 10000;
				System.out.println("COLIDIU!");
			}

		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		g.drawString("Pontos - " + cont, 40, 400);
	}

}
