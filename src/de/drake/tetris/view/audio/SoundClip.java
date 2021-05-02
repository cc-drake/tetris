package de.drake.tetris.view.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundClip {
	
	private final Clip clip;
	
	SoundClip(final String path) throws Exception {
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
		DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());
		this.clip = (Clip) AudioSystem.getLine(info);
		this.clip.open(audioStream);
	}
	
	public void play() {
		this.clip.start();
	}
	
}