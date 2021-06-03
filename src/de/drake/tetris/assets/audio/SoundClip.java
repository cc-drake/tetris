package de.drake.tetris.assets.audio;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

import de.drake.tetris.config.Config;
import de.drake.tetris.log.Logger;

public class SoundClip {
	
	private String path;
	
	public SoundClip(final String path) throws Exception {
			this.path = path;
	}
	
	public void play() {
		if (Config.sounds == false)
			return;
		try {
			InputStream is = SoundClip.class.getResourceAsStream(this.path);
			BufferedInputStream bis = new BufferedInputStream(is);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
			Clip clip = AudioSystem.getClip();
			clip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					clip.close();
				}
			});
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			Logger.writeThrowable(e);
			System.exit(-1);
		}
	}
	
}