package com.java.pitfall.main;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.java.pitfall.constants.Constants;import com.java.pitfall.stage.Stage1;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Collisions extends Screen implements Constants{
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	public Collisions() throws IOException, InterruptedException {
		//super();

	}

	public void checkCollision(){

		if(stage1){
			Rectangle fall =   this.stageOne.buraco();
			Rectangle p1   =   this.player.playerDim();

			if(fall.intersects(p1)){
				this.colid++;
				System.out.println("COLIDIU! - " + colid);
				player.x -= 30;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.y += (int) 400 * GRAVITY;
				song();
				player.y = 1000000;
			}
		}

	}

	
	//Efeitos sonoros
	public void song(){



		AudioData MD;

		ContinuousAudioDataStream loop;

		try
		{
			InputStream test = new FileInputStream("/home/bigboss/death.wav");
			BGM = new AudioStream(test);
			AudioPlayer.player.start(BGM);
			//MD = BGM.getData();
			//loop = new ContinuousAudioDataStream(MD);
			AudioStream s = new AudioStream(test);     
			AudioData audiodata = s.getData();
			loop = new ContinuousAudioDataStream(audiodata);
			AudioPlayer.player.start(BGM);
		}
		catch(FileNotFoundException e){
			System.out.print(e.toString());
		}
		catch(IOException error)
		{
			System.out.print(error.toString());
		}


	}

}
