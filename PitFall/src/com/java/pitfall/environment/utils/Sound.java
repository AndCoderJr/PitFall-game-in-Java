package com.java.pitfall.environment.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Sound {
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	public Sound(){
		
	}
	
	//Efeitos sonoros
	public void song(String path){
		AudioData MD;

		ContinuousAudioDataStream loop;

		try
		{
			InputStream test = new FileInputStream(path);
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
