package de.drake.tetris.assets.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

import de.drake.tetris.config.Config;

public class SoundClip {
	
	private File file;
	
	public SoundClip(final String path) {
		try {
			this.file = new File(SoundClip.class.getResource(path).toURI());
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	public void play() {
		if (Config.sounds == false)
			return;
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					clip.close();
				}
			});
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
}