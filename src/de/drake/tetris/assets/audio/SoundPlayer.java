package de.drake.tetris.assets.audio;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.sound.sampled.Clip;

import de.drake.tetris.log.Logger;

public class SoundPlayer implements Runnable {
	
	private final static SoundPlayer instance = new SoundPlayer();
	
	private final static int BUFFERS = 4;
	
	private final HashMap<SoundFile, ConcurrentLinkedQueue<Clip>> soundQueues;
	
	private SoundPlayer() {
		this.soundQueues = new HashMap<SoundFile, ConcurrentLinkedQueue<Clip>>();
	}
	
	public static void addSoundFile(final SoundFile soundFile) {
		SoundPlayer.instance.soundQueues.put(soundFile, new ConcurrentLinkedQueue<Clip>());
	}

	@Override
	public void run() {
		try {
			ConcurrentLinkedQueue<Clip> queue;
			while (true) {
				for (SoundFile file : this.soundQueues.keySet()) {
					queue = this.soundQueues.get(file);
					if (queue.size() < SoundPlayer.BUFFERS) {
						queue.offer(file.getClip());
					}
				}
				Thread.sleep(1);
			}
		} catch (Exception e) {
			Logger.writeThrowable(e);
			System.exit(-1);
		}
	}
	
	public static void start() {
		Thread thread = new Thread(SoundPlayer.instance, "SoundPlayer");
		thread.setDaemon(true);
		thread.start();
	}
	
	public static void play(final SoundFile file) {
		ConcurrentLinkedQueue<Clip> soundQueue = SoundPlayer.instance.soundQueues.get(file);
		while (soundQueue.isEmpty()) {
			Logger.write("Warning: Sound buffer for file " + file + "is empty. Waiting for 1 ms...");
			Logger.write("Please contact developer to increase the amount of sound buffers!");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				Logger.writeThrowable(e);
				System.exit(-1);
			}
		}
		SoundPlayer.instance.soundQueues.get(file).poll().start();
	}
	
}