package de.drake.tetris.input.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import de.drake.tetris.log.Logger;
import net.java.games.input.ControllerEnvironment;

public class JInputLoader {
	
	public static void load() throws Exception {
		String pathname = "lib";
		File libfolder = new File(pathname);
		if (!libfolder.exists()) {
			libfolder.mkdir();
		}
		
		JInputLoader.extract("jinput-dx8_64.dll", pathname);
		JInputLoader.extract("jinput-raw_64.dll", pathname);
		JInputLoader.extract("jinput-wintab.dll", pathname);
		
		System.setProperty("net.java.games.input.librarypath", libfolder.getAbsolutePath());
		
		if (ControllerEnvironment.getDefaultEnvironment().getControllers().length == 0) {
			Logger.write("Warnung: Beim Start des Moduls JInput ist ein Fehler aufgetreten.");
			Logger.write("Bitte installieren Sie die aktuelle 64-Bit-Version von Java.");
			Logger.write("Ohne das JInput-Modul ist die Nutzung von Gamepads nicht möglich.");
		}
	}
	
	private static File extract(final String filename, final String pathname) throws Exception {
		InputStream is = JInputLoader.class.getClassLoader().getResourceAsStream(filename);
		File file = new File(pathname + "/" + filename);
		if (file.exists()) {
			return file;
		}
		FileOutputStream os = new FileOutputStream(file);
		
		byte[] buffer = new byte[1024];
		int read = -1;
		while ((read = is.read(buffer)) != -1) {
			os.write(buffer, 0, read);
		}
		
		os.close();
		is.close();
		return file;
	}
	
}