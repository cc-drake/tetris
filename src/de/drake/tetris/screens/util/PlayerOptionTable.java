package de.drake.tetris.screens.util;

import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.input.util.InputManager;
import de.drake.tetris.input.util.Key;
import de.drake.tetris.model.util.Action;

public class PlayerOptionTable extends OptionTable implements ChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final PlayerConfig player;
	
	private final JTextField name;
	
	private final NumberSpinner speed;
	
	private final ListSpinner inputType;
	
	private KeyInputField left, right, down, drop, dreh_uzs, dreh_euzs, pause, quit;

	public PlayerOptionTable(final PlayerConfig player) {
		super();
		this.player = player;
		
		this.name = ComponentFactory.createJTextField(player.getName());
		super.addOption("Spielername", name);
		
		this.speed = new NumberSpinner(Config.initialSpeed, 0., Config.FPS, .1);
		super.addOption("Fallgeschwindigkeit", this.speed);
	
		this.inputType = new ListSpinner(InputDevice.allInputdevices.toArray(), 7);
		this.inputType.addChangeListener(this);
		super.addOption("Eingabegerät", this.inputType);
		
		this.left = new KeyInputField(inputType);
		super.addOption("Links", this.left);
		
		this.right = new KeyInputField(inputType);
		super.addOption("Rechts", this.right);
		
		this.down = new KeyInputField(inputType);
		super.addOption("Runter", this.down);
		
		this.drop = new KeyInputField(inputType);
		super.addOption("Absetzen", this.drop);
		
		this.dreh_uzs = new KeyInputField(inputType);
		super.addOption("Drehen (UZS)", this.dreh_uzs);
		
		this.dreh_euzs = new KeyInputField(inputType);
		super.addOption("Drehen (eUZS)", this.dreh_euzs);
		
		this.pause = new KeyInputField(inputType);
		super.addOption("Pause", this.pause);
		
		this.quit = new KeyInputField(inputType);
		super.addOption("Beenden", this.quit);
		
		//Initialisiert die Tastenbelegung
		this.stateChanged(null);
	}
	
	public void paintComponent(Graphics g) {
		if (this.name.getText().isEmpty()) {
			this.player.setName("Namenloser Spieler");
		} else {
			this.player.setName(this.name.getText());
		}
		super.paintComponent(g);
	}

	public void initializePlayer() {
		this.player.setSpeed(this.speed.getDoubleValue());
		HashMap<Key, Action> tastenbelegung = new HashMap<Key, Action>();
		tastenbelegung.put(this.left.getKey(), Action.LINKS);
		tastenbelegung.put(this.right.getKey(), Action.RECHTS);
		tastenbelegung.put(this.down.getKey(), Action.RUNTER);
		tastenbelegung.put(this.drop.getKey(), Action.GANZ_RUNTER);
		tastenbelegung.put(this.dreh_uzs.getKey(), Action.DREHUNG_UHRZEIGERSINN);
		tastenbelegung.put(this.dreh_euzs.getKey(), Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		tastenbelegung.put(this.pause.getKey(), Action.PAUSE);
		tastenbelegung.put(this.quit.getKey(), Action.QUIT);
		this.player.setInputManager(new InputManager(
				(InputDevice) this.inputType.getValue(), tastenbelegung));
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		InputDevice device = (InputDevice) this.inputType.getValue();
		switch (device.getType()) {
		case InputDevice.KEYBOARD:
			this.left.setKey(PlayerConfig.keyboard_left);
			this.right.setKey(PlayerConfig.keyboard_right);
			this.down.setKey(PlayerConfig.keyboard_down);
			this.drop.setKey(PlayerConfig.keyboard_drop);
			this.dreh_uzs.setKey(PlayerConfig.keyboard_dreh_uzs);
			this.dreh_euzs.setKey(PlayerConfig.keyboard_dreh_euzs);
			this.pause.setKey(PlayerConfig.keyboard_pause);
			this.quit.setKey(PlayerConfig.keyboard_quit);
			break;
		case InputDevice.MOUSE:
			this.left.setKey(PlayerConfig.mouse_left);
			this.right.setKey(PlayerConfig.mouse_right);
			this.down.setKey(PlayerConfig.mouse_down);
			this.drop.setKey(PlayerConfig.mouse_drop);
			this.dreh_uzs.setKey(PlayerConfig.mouse_dreh_uzs);
			this.dreh_euzs.setKey(PlayerConfig.mouse_dreh_euzs);
			this.pause.setKey(PlayerConfig.mouse_pause);
			this.quit.setKey(PlayerConfig.mouse_quit);
			break;
		case InputDevice.GAMEPAD:
			this.left.setKey(PlayerConfig.gamepad_left);
			this.right.setKey(PlayerConfig.gamepad_right);
			this.down.setKey(PlayerConfig.gamepad_down);
			this.drop.setKey(PlayerConfig.gamepad_drop);
			this.dreh_uzs.setKey(PlayerConfig.gamepad_dreh_uzs);
			this.dreh_euzs.setKey(PlayerConfig.gamepad_dreh_euzs);
			this.pause.setKey(PlayerConfig.gamepad_pause);
			this.quit.setKey(PlayerConfig.gamepad_quit);
			break;
		}
	}
	
}
