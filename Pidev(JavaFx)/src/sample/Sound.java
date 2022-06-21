package sample;

import java.io.File;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sound {
	private final static String badSound = "../error.wav";
	private final static String goodSound = "../toggle.wav";
	
	public void errorSound() throws Exception{
	    File yourFile = new File(badSound);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}

	public void successSound() throws Exception{
	    File yourFile = new File(goodSound);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}
}
