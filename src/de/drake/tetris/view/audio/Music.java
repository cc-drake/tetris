package de.drake.tetris.view.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Music {
	
	private final static int BUFFER_SIZE = 4096;
	
	private final File file;
	
	Music(final String path) throws Exception {
		this.file = new File(path);
	}
	
	public void play() throws Exception {
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(this.file);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioStream.getFormat());
		SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
		audioLine.open(audioStream.getFormat());
		audioLine.start();
		
		byte[] bytesBuffer = new byte[Music.BUFFER_SIZE];
		int bytesRead = -1;
		while((bytesRead = audioStream.read(bytesBuffer)) != -1) {
			audioLine.write(bytesBuffer, 0, bytesRead);
		}
		
		audioLine.drain();
		audioLine.close();
		audioStream.close();
	}
	
}