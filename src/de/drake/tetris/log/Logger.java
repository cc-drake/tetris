package de.drake.tetris.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

public class Logger {
	
	private static FileWriter writer;
	
	public static void init() {
		try {
			Logger.writer = new FileWriter("Tetris.log", false);
			
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

				@Override
				public void uncaughtException(Thread t, Throwable e) {
					Logger.write("Nicht abgefangener Fehler in Thread " + t.getName());
					Logger.writeThrowable(e);
					System.exit(-1);
				}
				
			});
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static void write(final String text) {
		try {
			System.out.println(text);
			Logger.writer.write(text + "\r\n");
			Logger.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static void writeThrowable(final Throwable e) {
		e.printStackTrace();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		Logger.write(sw.toString());
	}
	
}