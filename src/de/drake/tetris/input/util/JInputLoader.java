package de.drake.tetris.input.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class JInputLoader {
	
	public static void loadDLLs() throws Exception {
		JInputLoader.load("jinput-dx8_64.dll");
		JInputLoader.load("jinput-raw_64.dll");
		JInputLoader.load("jinput-wintab.dll");
	}
	
	private static void load(final String filename) throws Exception {
		InputStream is = JInputLoader.class.getClassLoader().getResourceAsStream(filename);
		File file = File.createTempFile(filename, "");
		FileOutputStream os = new FileOutputStream(file);
		
		byte[] buffer = new byte[1024];
		int read = -1;
		while ((read = is.read(buffer)) != -1) {
			os.write(buffer, 0, read);
		}
		
		os.close();
		is.close();
		
		System.load(file.getAbsolutePath());
	}
	
}