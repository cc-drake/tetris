package de.drake.tetris.assets.audio;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public class SoundFile {
	
	private String path;
	
	public SoundFile(final String path) throws Exception {
		this.path = path;
	}
	
	Clip getClip() throws Exception {
		InputStream is = SoundFile.class.getResourceAsStream(this.path);
		BufferedInputStream bis = new BufferedInputStream(is);
		AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
		Clip clip = AudioSystem.getClip();
		clip.addLineListener(event -> {
			if (event.getType() == LineEvent.Type.STOP) {
				clip.close();
			}
		});
		clip.open(ais);
		clip.drain();
		return clip;
	}
	
	@Override
	public String toString() {
		return this.path;
	}
	
}