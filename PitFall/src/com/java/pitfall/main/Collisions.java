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
	public Collisions() throws IOException, InterruptedException {
		//super();
		song = new Sound();

	}

	public void checkCollision(){
		Rectangle fall =   this.stageOne.buraco();
		Rectangle p1   =   this.player.playerDim();
		Rectangle barril1 = this.stageOne.barril();
		Rectangle barril2 = this.stageOne.barril2();
		if(stage1){
			if(barril1.intersects(p1)){
				this.cont++;
			}

			if(barril2.intersects(p1)){
				this.cont++;
			}

			if(fall.intersects(p1)){
				this.cont++;

				System.out.println("COLIDIU! - " + cont);
				player.x -= 30;
				player.y += (int) 50 * GRAVITY;

				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.y += (int) 400 * GRAVITY;
				song.song("/home/bigboss/death.wav");
				player.y = 1000000;
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}




			}

		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		g.drawString("Pontos - " + cont, 40, 400);
	}

}
