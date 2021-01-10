package de.drake.tetris.config;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import de.drake.tetris.input.util.Key;

public class IniInOut {
	
	public static void initConfig() {
		
		HashMap<String, String> parameters = null;
		
		try {
			parameters = IniInOut.readIni();
		} catch (IOException e) {
		}
		
		IniInOut.setConfig(parameters);
		
		try {
			IniInOut.saveIni();
		} catch (IOException e) {
		}
		
	}
	
	private static HashMap<String, String> readIni() throws IOException {
		HashMap<String, String> result = new HashMap<String, String>();
		FileReader filereader = new FileReader("Tetris.ini");
		Scanner scanner = new Scanner(filereader);
		String line;
		String[] splitLine;
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			if (line.startsWith("//"))
				continue;
			splitLine = line.split("=");
			if (splitLine.length == 2)
				result.put(splitLine[0].trim(), splitLine[1].trim());
		}
		scanner.close();
		filereader.close();
		return result;
	}
	
	public static void setConfig(HashMap<String, String> parameters) {
		if (parameters == null)
			parameters = new HashMap<String, String>();
		
		String value;
		
		value = parameters.get("stone_small");
		try {
			Config.stone_small = Integer.parseInt(value);
		} catch (Exception e) {
			Config.stone_small = 2;
		}
		
		value = parameters.get("stone_regular");
		try {
			Config.stone_regular = Integer.parseInt(value);
		} catch (Exception e) {
			Config.stone_regular = 6;
		}
		
		value = parameters.get("stone_large");
		try {
			Config.stone_large = Integer.parseInt(value);
		} catch (Exception e) {
			Config.stone_large = 3;
		}
		
		value = parameters.get("stone_bomb");
		try {
			Config.stone_bomb = Integer.parseInt(value);
		} catch (Exception e) {
			Config.stone_bomb = 1;
		}
		
		value = parameters.get("breite");
		try {
			Config.breite = Integer.parseInt(value);
		} catch (Exception e) {
			Config.breite = 10;
		}
		
		value = parameters.get("hoehe");
		try {
			Config.hoehe = Integer.parseInt(value);
		} catch (Exception e) {
			Config.hoehe = 20;
		}
		
		value = parameters.get("initialSpeed");
		try {
			Config.initialSpeed = Double.parseDouble(value);
		} catch (Exception e) {
			Config.initialSpeed = 2.;
		}
		
		value = parameters.get("keyRepeatDelay");
		try {
			Config.keyRepeatDelay = Integer.parseInt(value);
		} catch (Exception e) {
			Config.keyRepeatDelay = 170;
		}
		
		value = parameters.get("keyRepeatSpeed");
		try {
			Config.keyRepeatSpeed = Integer.parseInt(value);
		} catch (Exception e) {
			Config.keyRepeatSpeed = 25;
		}
		
		value = parameters.get("timeLimitCombat");
		try {
			Config.timeLimitCombat = Integer.parseInt(value);
		} catch (Exception e) {
			Config.timeLimitCombat = 0;
		}
		
		value = parameters.get("timeLimitRace");
		try {
			Config.timeLimitRace = Integer.parseInt(value);
		} catch (Exception e) {
			Config.timeLimitRace = 0;
		}
		
		value = parameters.get("timeLimitCheese");
		try {
			Config.timeLimitCheese = Integer.parseInt(value);
		} catch (Exception e) {
			Config.timeLimitCheese = 0;
		}
		
		value = parameters.get("speedIncreaseRow");
		try {
			Config.speedIncreaseRow = Double.parseDouble(value);
		} catch (Exception e) {
			Config.speedIncreaseRow = 1.;
		}
		
		value = parameters.get("speedIncreaseSec");
		try {
			Config.speedIncreaseSec = Double.parseDouble(value);
		} catch (Exception e) {
			Config.speedIncreaseSec = 0.5;
		}
		
		value = parameters.get("combatType");
		try {
			switch (value) {
			case GameMode.COMBAT_BADASS:
				Config.combatType = GameMode.COMBAT_BADASS;
				break;
			case GameMode.COMBAT_CLASSIC:
				Config.combatType = GameMode.COMBAT_CLASSIC;
				break;
			case GameMode.COMBAT_PEACE:
				Config.combatType = GameMode.COMBAT_PEACE;
				break;
			default:
				throw (new Exception());
			}
		} catch (Exception e) {
			Config.combatType = GameMode.COMBAT_CLASSIC;
		}
		
		value = parameters.get("raceRows");
		try {
			Config.raceRows = Integer.parseInt(value);
		} catch (Exception e) {
			Config.raceRows = 50;
		}
		
		value = parameters.get("cheeseRows");
		try {
			Config.cheeseRows = Integer.parseInt(value);
		} catch (Exception e) {
			Config.cheeseRows = 9;
		}
		
		try {
			Config.keyboard_left = new Key(
					Integer.parseInt(parameters.get("keyboard_left_ID")),
					parameters.get("keyboard_left_description"));
		} catch (Exception e) {
			Config.keyboard_left = new Key(KeyEvent.VK_A, "A");
		}
		
		try {
			Config.keyboard_right = new Key(
					Integer.parseInt(parameters.get("keyboard_right_ID")),
					parameters.get("keyboard_right_description"));
		} catch (Exception e) {
			Config.keyboard_right = new Key(KeyEvent.VK_D, "D");
		}
		
		try {
			Config.keyboard_down = new Key(
					Integer.parseInt(parameters.get("keyboard_down_ID")),
					parameters.get("keyboard_down_description"));
		} catch (Exception e) {
			Config.keyboard_down = new Key(KeyEvent.VK_S, "S");
		}
		
		try {
			Config.keyboard_drop = new Key(
					Integer.parseInt(parameters.get("keyboard_drop_ID")),
					parameters.get("keyboard_drop_description"));
		} catch (Exception e) {
			Config.keyboard_drop = new Key(KeyEvent.VK_SPACE, "Leertaste");
		}
		
		try {
			Config.keyboard_dreh_uzs = new Key(
					Integer.parseInt(parameters.get("keyboard_dreh_uzs_ID")),
					parameters.get("keyboard_dreh_uzs_description"));
		} catch (Exception e) {
			Config.keyboard_dreh_uzs = new Key(KeyEvent.VK_NUMPAD5, "Numpad-5");
		}
		
		try {
			Config.keyboard_dreh_euzs = new Key(
					Integer.parseInt(parameters.get("keyboard_dreh_euzs_ID")),
					parameters.get("keyboard_dreh_euzs_description"));
		} catch (Exception e) {
			Config.keyboard_dreh_euzs = new Key(KeyEvent.VK_NUMPAD4, "Numpad-4");
		}
		
		try {
			Config.keyboard_pause = new Key(
					Integer.parseInt(parameters.get("keyboard_pause_ID")),
					parameters.get("keyboard_pause_description"));
		} catch (Exception e) {
			Config.keyboard_pause = new Key(KeyEvent.VK_ENTER, "Eingabe");
		}
		
		try {
			Config.keyboard_quit = new Key(
					Integer.parseInt(parameters.get("keyboard_quit_ID")),
					parameters.get("keyboard_quit_description"));
		} catch (Exception e) {
			Config.keyboard_quit = new Key(KeyEvent.VK_ESCAPE, "ESC");
		}
		
		try {
			Config.mouse_left = new Key(
					Integer.parseInt(parameters.get("mouse_left_ID")),
					parameters.get("mouse_left_description"));
		} catch (Exception e) {
			Config.mouse_left = new Key(MouseEvent.BUTTON1, "Links");
		}
		
		try {
			Config.mouse_right = new Key(
					Integer.parseInt(parameters.get("mouse_right_ID")),
					parameters.get("mouse_right_description"));
		} catch (Exception e) {
			Config.mouse_right = new Key(MouseEvent.BUTTON3, "Rechts");
		}
		
		try {
			Config.mouse_down = new Key(
					Integer.parseInt(parameters.get("mouse_down_ID")),
					parameters.get("mouse_down_description"));
		} catch (Exception e) {
			Config.mouse_down = null;
		}
		
		try {
			Config.mouse_drop = new Key(
					Integer.parseInt(parameters.get("mouse_drop_ID")),
					parameters.get("mouse_drop_description"));
		} catch (Exception e) {
			Config.mouse_drop = new Key(MouseEvent.BUTTON2, "Mitte");
		}
		
		try {
			Config.mouse_dreh_uzs = new Key(
					Integer.parseInt(parameters.get("mouse_dreh_uzs_ID")),
					parameters.get("mouse_dreh_uzs_description"));
		} catch (Exception e) {
			Config.mouse_dreh_uzs = new Key(-1, "Scroll ab");
		}
		
		try {
			Config.mouse_dreh_euzs = new Key(
					Integer.parseInt(parameters.get("mouse_dreh_euzs_ID")),
					parameters.get("mouse_dreh_euzs_description"));
		} catch (Exception e) {
			Config.mouse_dreh_euzs = new Key(0, "Scroll auf");
		}
		
		try {
			Config.mouse_pause = new Key(
					Integer.parseInt(parameters.get("mouse_pause_ID")),
					parameters.get("mouse_pause_description"));
		} catch (Exception e) {
			Config.mouse_pause = null;
		}
		
		try {
			Config.mouse_quit = new Key(
					Integer.parseInt(parameters.get("mouse_quit_ID")),
					parameters.get("mouse_quit_description"));
		} catch (Exception e) {
			Config.mouse_quit = null;
		}
		
		try {
			Config.gamepad_left = new Key(
					Integer.parseInt(parameters.get("gamepad_left_ID")),
					parameters.get("gamepad_left_description"));
		} catch (Exception e) {
			Config.gamepad_left = new Key(KeyEvent.VK_LEFT, "Links");
		}
		
		try {
			Config.gamepad_right = new Key(
					Integer.parseInt(parameters.get("gamepad_right_ID")),
					parameters.get("gamepad_right_description"));
		} catch (Exception e) {
			Config.gamepad_right = new Key(KeyEvent.VK_RIGHT, "Rechts");
		}
		
		try {
			Config.gamepad_down = new Key(
					Integer.parseInt(parameters.get("gamepad_down_ID")),
					parameters.get("gamepad_down_description"));
		} catch (Exception e) {
			Config.gamepad_down = new Key(KeyEvent.VK_DOWN, "Unten");
		}
		
		try {
			Config.gamepad_drop = new Key(
					Integer.parseInt(parameters.get("gamepad_drop_ID")),
					parameters.get("gamepad_drop_description"));
		} catch (Exception e) {
			Config.gamepad_drop = new Key(KeyEvent.VK_5, "Taste 5");
		}
		
		try {
			Config.gamepad_dreh_uzs = new Key(
					Integer.parseInt(parameters.get("gamepad_dreh_uzs_ID")),
					parameters.get("gamepad_dreh_uzs_description"));
		} catch (Exception e) {
			Config.gamepad_dreh_uzs = new Key(KeyEvent.VK_1, "Taste 1");
		}
		
		try {
			Config.gamepad_dreh_euzs = new Key(
					Integer.parseInt(parameters.get("gamepad_dreh_euzs_ID")),
					parameters.get("gamepad_dreh_euzs_description"));
		} catch (Exception e) {
			Config.gamepad_dreh_euzs = new Key(KeyEvent.VK_0, "Taste 0");
		}
		
		try {
			Config.gamepad_pause = new Key(
					Integer.parseInt(parameters.get("gamepad_pause_ID")),
					parameters.get("gamepad_pause_description"));
		} catch (Exception e) {
			Config.gamepad_pause = new Key(KeyEvent.VK_7, "Taste 7");
		}
		
		try {
			Config.gamepad_quit = new Key(
					Integer.parseInt(parameters.get("gamepad_quit_ID")),
					parameters.get("gamepad_quit_description"));
		} catch (Exception e) {
			Config.gamepad_quit = new Key(KeyEvent.VK_6, "Taste 6");
		}
		
	}
	
	public static void saveIni() throws IOException {
		String ini = "";
		ini += "// Parameter werden beim Start von Tetris eingelesen. Kommentare mit \"//\" einrücken.\r\n";
		ini += "\r\n";
		
		ini += "// Häufigkeit kleiner Steine aus 3 oder weniger Teilen.\r\n";
		ini += "stone_small = " + Config.stone_small + "\r\n";
		ini += "\r\n";
		
		ini += "// Häufigkeit normaler Steine aus 4 Teilen.\r\n";
		ini += "stone_regular = " + Config.stone_regular + "\r\n";
		ini += "\r\n";
		
		ini += "// Häufigkeit großer Steine aus 5 Teilen.\r\n";
		ini += "stone_large = " + Config.stone_large + "\r\n";
		ini += "\r\n";
		
		ini += "// Häufigkeit von Bomben.\r\n";
		ini += "stone_bomb = " + Config.stone_bomb + "\r\n";
		ini += "\r\n";
		
		ini += "// Die Breite des Spielfelds.\r\n";
		ini += "breite = " + Config.breite + "\r\n";
		ini += "\r\n";
		
		ini += "// Die Höhe des Spielfelds.\r\n";
		ini += "hoehe = " + Config.hoehe + "\r\n";
		ini += "\r\n";
		
		ini += "// Die initiale Fallgeschwindigkeit der Steine in Feldern pro Sekunde.\r\n";
		ini += "initialSpeed = " + Config.initialSpeed + "\r\n";
		ini += "\r\n";
		
		ini += "// Die Verzögerung der Tastenwiederholung in Millisekunden.\r\n";
		ini += "keyRepeatDelay = " + Config.keyRepeatDelay + "\r\n";
		ini += "\r\n";
		
		ini += "// Die Geschwindigkeit der Tastenwiederholung in Feldern pro Sekunde.\r\n";
		ini += "keyRepeatSpeed = " + Config.keyRepeatSpeed + "\r\n";
		ini += "\r\n";
		
		ini += "// Das Zeitlimit für den Combat-Modus in Sekunden.\r\n";
		ini += "timeLimit = " + Config.timeLimitCombat + "\r\n";
		ini += "\r\n";
		
		ini += "// Das Zeitlimit für den Race-Modus in Sekunden.\r\n";
		ini += "timeLimit = " + Config.timeLimitRace + "\r\n";
		ini += "\r\n";
		
		ini += "// Das Zeitlimit für den Cheese-Modus in Sekunden.\r\n";
		ini += "timeLimit = " + Config.timeLimitCheese + "\r\n";
		ini += "\r\n";
		
		ini += "// Die Erhöhung der Spielgeschwindigkeit je entfernter Reihe in % (Solitär-Modus).\r\n";
		ini += "speedIncreaseRow = " + Config.speedIncreaseRow + "\r\n";
		ini += "\r\n";
		
		ini += "// Die Erhöhung der Spielgeschwindigkeit je Sekunde in % (Combat-Modus).\r\n";
		ini += "speedIncreaseSec = " + Config.speedIncreaseSec + "\r\n";
		ini += "\r\n";
		
		ini += "// Der Battlemode im Combat-Modus.\r\n";
		ini += "combatType = " + Config.combatType + "\r\n";
		ini += "\r\n";
		
		ini += "// Die Zahl der zu eliminierenden Reihen (Race-Modus).\r\n";
		ini += "raceRows = " + Config.raceRows + "\r\n";
		ini += "\r\n";
		
		ini += "// Die Zahl der zu eliminierenden Cheese-Reihen (Cheese-Modus).\r\n";
		ini += "cheeseRows = " + Config.cheeseRows + "\r\n";
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Tastatur für \"Links\".\r\n";
		ini += IniInOut.getKeyString("keyboard_left", Config.keyboard_left);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Tastatur für \"Rechts\".\r\n";
		ini += IniInOut.getKeyString("keyboard_right", Config.keyboard_right);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Tastatur für \"Runter\".\r\n";
		ini += IniInOut.getKeyString("keyboard_down", Config.keyboard_down);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Tastatur für \"Absetzen\".\r\n";
		ini += IniInOut.getKeyString("keyboard_drop", Config.keyboard_drop);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Tastatur für \"Drehen im Uhrzeigersinn\".\r\n";
		ini += IniInOut.getKeyString("keyboard_dreh_uzs", Config.keyboard_dreh_uzs);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Tastatur für \"Drehen gegen den Uhrzeigersinn\".\r\n";
		ini += IniInOut.getKeyString("keyboard_dreh_euzs", Config.keyboard_dreh_euzs);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Tastatur für \"Pause\".\r\n";
		ini += IniInOut.getKeyString("keyboard_pause", Config.keyboard_pause);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Tastatur für \"Beenden\".\r\n";
		ini += IniInOut.getKeyString("keyboard_quit", Config.keyboard_quit);
		ini += "\r\n";

		ini += "// Default-Tastenbelegung der Maus für \"Links\".\r\n";
		ini += IniInOut.getKeyString("mouse_left", Config.mouse_left);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Maus für \"Rechts\".\r\n";
		ini += IniInOut.getKeyString("mouse_right", Config.mouse_right);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Maus für \"Runter\".\r\n";
		ini += IniInOut.getKeyString("mouse_down", Config.mouse_down);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Maus für \"Absetzen\".\r\n";
		ini += IniInOut.getKeyString("mouse_drop", Config.mouse_drop);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Maus für \"Drehen im Uhrzeigersinn\".\r\n";
		ini += IniInOut.getKeyString("mouse_dreh_uzs", Config.mouse_dreh_uzs);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Maus für \"Drehen gegen den Uhrzeigersinn\".\r\n";
		ini += IniInOut.getKeyString("mouse_dreh_euzs", Config.mouse_dreh_euzs);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Maus für \"Pause\".\r\n";
		ini += IniInOut.getKeyString("mouse_pause", Config.mouse_pause);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung der Maus für \"Beenden\".\r\n";
		ini += IniInOut.getKeyString("mouse_quit", Config.mouse_quit);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung des Gamepads für \"Links\".\r\n";
		ini += IniInOut.getKeyString("gamepad_left", Config.gamepad_left);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung des Gamepads für \"Rechts\".\r\n";
		ini += IniInOut.getKeyString("gamepad_right", Config.gamepad_right);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung des Gamepads für \"Runter\".\r\n";
		ini += IniInOut.getKeyString("gamepad_down", Config.gamepad_down);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung des Gamepads für \"Absetzen\".\r\n";
		ini += IniInOut.getKeyString("gamepad_drop", Config.gamepad_drop);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung des Gamepads für \"Drehen im Uhrzeigersinn\".\r\n";
		ini += IniInOut.getKeyString("gamepad_dreh_uzs", Config.gamepad_dreh_uzs);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung des Gamepads für \"Drehen gegen den Uhrzeigersinn\".\r\n";
		ini += IniInOut.getKeyString("gamepad_dreh_euzs", Config.gamepad_dreh_euzs);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung des Gamepads für \"Pause\".\r\n";
		ini += IniInOut.getKeyString("gamepad_pause", Config.gamepad_pause);
		ini += "\r\n";
		
		ini += "// Default-Tastenbelegung des Gamepads für \"Beenden\".\r\n";
		ini += IniInOut.getKeyString("gamepad_quit", Config.gamepad_quit);
		ini += "\r\n";
		
		FileWriter filewriter = new FileWriter("Tetris.ini", false);
		filewriter.write(ini);
		filewriter.flush();
		filewriter.close();
	}
	
	private static String getKeyString(final String type, final Key key) {
		String result = "";
		if (key == null) {
			result += type + "_ID = \r\n";
			result += type + "_description = \r\n";
		} else {
			result += type + "_ID = " + key.getID() + "\r\n";
			result += type + "_description = " + key.getDescription() + "\r\n";
		}
		return result;
	}
	
}