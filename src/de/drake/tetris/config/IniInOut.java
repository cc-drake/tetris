package de.drake.tetris.config;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

import de.drake.tetris.input.util.Key;

public class IniInOut {
	
	private final HashMap<String, String> parameter2value = new HashMap<String, String>();
	
	public static void initConfig() {
		IniInOut ini = new IniInOut();
		ini.readIni();
		ini.setConfig();
	}
	
	private void readIni() {
		FileReader filereader;
		try {
			filereader = new FileReader("Tetris.ini");
		} catch (Exception e) {
			return;
		}
		Scanner scanner = new Scanner(filereader);
		String line;
		String[] splitLine;
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			if (line.startsWith("//"))
				continue;
			splitLine = line.split("=");
			if (splitLine.length == 2)
				this.parameter2value.put(splitLine[0].trim(), splitLine[1].trim());
		}
		scanner.close();
		try {
			filereader.close();
		} catch (Exception e) {
		}
	}
	
	private void setConfig() {
		String value;
		
		value = this.getValue("stone_small");
		try {
			Config.stone_small = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Config.stone_small = 2;
		}
		
		value = this.getValue("stone_regular");
		try {
			Config.stone_regular = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Config.stone_regular = 6;
		}
		
		value = this.getValue("stone_large");
		try {
			Config.stone_large = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Config.stone_large = 3;
		}
		
		value = this.getValue("stone_bomb");
		try {
			Config.stone_bomb = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Config.stone_bomb = 1;
		}
		
		value = this.getValue("breite");
		try {
			Config.breite = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Config.breite = 10;
		}
		
		value = this.getValue("hoehe");
		try {
			Config.hoehe = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Config.hoehe = 20;
		}
		
		value = this.getValue("keyRepeatDelay");
		try {
			Config.keyRepeatDelay = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Config.keyRepeatDelay = 170;
		}
		
		value = this.getValue("keyRepeatSpeed");
		try {
			Config.keyRepeatSpeed = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			Config.keyRepeatSpeed = 25;
		}
		
		value = this.getValue("timeLimit");
		try {
			GameMode.timeLimit = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			GameMode.timeLimit = 0;
		}
		
		value = this.getValue("speedIncreaseRow");
		try {
			GameMode.speedIncreaseRow = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			GameMode.speedIncreaseRow = 1.;
		}
		
		value = this.getValue("speedIncreaseSec");
		try {
			GameMode.speedIncreaseSec = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			GameMode.speedIncreaseSec = 0.5;
		}
		
		value = this.getValue("raceRows");
		try {
			GameMode.raceRows = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			GameMode.raceRows = 50;
		}
		
		value = this.getValue("cheeseRows");
		try {
			GameMode.cheeseRows = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			GameMode.cheeseRows = 9;
		}
		
		value = this.getValue("initialSpeed");
		try {
			PlayerConfig.initialSpeed = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			PlayerConfig.initialSpeed = 2.;
		}
		
		try {
			PlayerConfig.keyboard_left = new Key(
					Integer.parseInt(this.getValue("keyboard_left_ID")),
					this.getValue("keyboard_left_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.keyboard_left = new Key(KeyEvent.VK_A, "A");
		}
		
		try {
			PlayerConfig.keyboard_right = new Key(
					Integer.parseInt(this.getValue("keyboard_right_ID")),
					this.getValue("keyboard_right_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.keyboard_right = new Key(KeyEvent.VK_D, "D");
		}
		
		try {
			PlayerConfig.keyboard_down = new Key(
					Integer.parseInt(this.getValue("keyboard_down_ID")),
					this.getValue("keyboard_down_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.keyboard_down = new Key(KeyEvent.VK_S, "S");
		}
		
		try {
			PlayerConfig.keyboard_drop = new Key(
					Integer.parseInt(this.getValue("keyboard_drop_ID")),
					this.getValue("keyboard_drop_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.keyboard_drop = new Key(KeyEvent.VK_SPACE, "Leertaste");
		}
		
		try {
			PlayerConfig.keyboard_dreh_uzs = new Key(
					Integer.parseInt(this.getValue("keyboard_dreh_uzs_ID")),
					this.getValue("keyboard_dreh_uzs_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.keyboard_dreh_uzs = new Key(KeyEvent.VK_NUMPAD5, "Numpad-5");
		}
		
		try {
			PlayerConfig.keyboard_dreh_euzs = new Key(
					Integer.parseInt(this.getValue("keyboard_dreh_euzs_ID")),
					this.getValue("keyboard_dreh_euzs_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.keyboard_dreh_euzs = new Key(KeyEvent.VK_NUMPAD4, "Numpad-4");
		}
		
		try {
			PlayerConfig.keyboard_pause = new Key(
					Integer.parseInt(this.getValue("keyboard_pause_ID")),
					this.getValue("keyboard_pause_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.keyboard_pause = new Key(KeyEvent.VK_ENTER, "Eingabe");
		}
		
		try {
			PlayerConfig.keyboard_quit = new Key(
					Integer.parseInt(this.getValue("keyboard_quit_ID")),
					this.getValue("keyboard_quit_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.keyboard_quit = new Key(KeyEvent.VK_ESCAPE, "ESC");
		}
		
		try {
			PlayerConfig.mouse_left = new Key(
					Integer.parseInt(this.getValue("mouse_left_ID")),
					this.getValue("mouse_left_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.mouse_left = new Key(MouseEvent.BUTTON1, "Links");
		}
		
		try {
			PlayerConfig.mouse_right = new Key(
					Integer.parseInt(this.getValue("mouse_right_ID")),
					this.getValue("mouse_right_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.mouse_right = new Key(MouseEvent.BUTTON3, "Rechts");
		}
		
		try {
			PlayerConfig.mouse_down = new Key(
					Integer.parseInt(this.getValue("mouse_down_ID")),
					this.getValue("mouse_down_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.mouse_down = null;
		}
		
		try {
			PlayerConfig.mouse_drop = new Key(
					Integer.parseInt(this.getValue("mouse_drop_ID")),
					this.getValue("mouse_drop_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.mouse_drop = new Key(MouseEvent.BUTTON2, "Mitte");
		}
		
		try {
			PlayerConfig.mouse_dreh_uzs = new Key(
					Integer.parseInt(this.getValue("mouse_dreh_uzs_ID")),
					this.getValue("mouse_dreh_uzs_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.mouse_dreh_uzs = new Key(-1, "Scroll ab");
		}
		
		try {
			PlayerConfig.mouse_dreh_euzs = new Key(
					Integer.parseInt(this.getValue("mouse_dreh_euzs_ID")),
					this.getValue("mouse_dreh_euzs_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.mouse_dreh_euzs = new Key(0, "Scroll auf");
		}
		
		try {
			PlayerConfig.mouse_pause = new Key(
					Integer.parseInt(this.getValue("mouse_pause_ID")),
					this.getValue("mouse_pause_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.mouse_pause = null;
		}
		
		try {
			PlayerConfig.mouse_quit = new Key(
					Integer.parseInt(this.getValue("mouse_quit_ID")),
					this.getValue("mouse_quit_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.mouse_quit = null;
		}
		
		try {
			PlayerConfig.gamepad_left = new Key(
					Integer.parseInt(this.getValue("gamepad_left_ID")),
					this.getValue("gamepad_left_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.gamepad_left = new Key(KeyEvent.VK_LEFT, "Links");
		}
		
		try {
			PlayerConfig.gamepad_right = new Key(
					Integer.parseInt(this.getValue("gamepad_right_ID")),
					this.getValue("gamepad_right_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.gamepad_right = new Key(KeyEvent.VK_RIGHT, "Rechts");
		}
		
		try {
			PlayerConfig.gamepad_down = new Key(
					Integer.parseInt(this.getValue("gamepad_down_ID")),
					this.getValue("gamepad_down_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.gamepad_down = new Key(KeyEvent.VK_DOWN, "Unten");
		}
		
		try {
			PlayerConfig.gamepad_drop = new Key(
					Integer.parseInt(this.getValue("gamepad_drop_ID")),
					this.getValue("gamepad_drop_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.gamepad_drop = new Key(KeyEvent.VK_5, "Taste 5");
		}
		
		try {
			PlayerConfig.gamepad_dreh_uzs = new Key(
					Integer.parseInt(this.getValue("gamepad_dreh_uzs_ID")),
					this.getValue("gamepad_dreh_uzs_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.gamepad_dreh_uzs = new Key(KeyEvent.VK_1, "Taste 1");
		}
		
		try {
			PlayerConfig.gamepad_dreh_euzs = new Key(
					Integer.parseInt(this.getValue("gamepad_dreh_euzs_ID")),
					this.getValue("gamepad_dreh_euzs_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.gamepad_dreh_euzs = new Key(KeyEvent.VK_0, "Taste 0");
		}
		
		try {
			PlayerConfig.gamepad_pause = new Key(
					Integer.parseInt(this.getValue("gamepad_pause_ID")),
					this.getValue("gamepad_pause_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.gamepad_pause = new Key(KeyEvent.VK_7, "Taste 7");
		}
		
		try {
			PlayerConfig.gamepad_quit = new Key(
					Integer.parseInt(this.getValue("gamepad_quit_ID")),
					this.getValue("gamepad_quit_description"));
		} catch (NumberFormatException e) {
			PlayerConfig.gamepad_quit = new Key(KeyEvent.VK_6, "Taste 6");
		}
		
	}
	
	private String getValue(final String parameter) {
		return this.parameter2value.get(parameter);
	}
	
}