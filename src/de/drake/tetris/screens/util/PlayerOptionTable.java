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
	 * Die Default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	private final PlayerConfig playerConfig;
	
	private final JTextField name;
	
	private final NumberSpinner initialSpeed;
	
	private final ListSpinner inputType;
	
	private KeyInputField left, right, down, drop, dreh_uzs, dreh_euzs, pause, quit;

	public PlayerOptionTable(final PlayerConfig playerConfig) {
		super();
		this.playerConfig = playerConfig;
		
		this.name = ComponentFactory.createJTextField(playerConfig.getName());
		super.addOption("Spielername", name);
		
		this.initialSpeed = new NumberSpinner(Config.initialSpeed, 0., Config.FPS, .1);
		super.addOption("Fallgeschwindigkeit", this.initialSpeed);
	
		this.inputType = new ListSpinner(InputDevice.allInputdevices.toArray(), 7, InputDevice.allInputdevices.get(0));
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
			this.playerConfig.setName("Namenloser Spieler");
		} else {
			this.playerConfig.setName(this.name.getText());
		}
		super.paintComponent(g);
	}

	public void initializePlayer() {
		this.playerConfig.setInitialSpeed(this.initialSpeed.getDoubleValue());
		HashMap<Key, Action> tastenbelegung = new HashMap<Key, Action>();
		tastenbelegung.put(this.left.getKey(), Action.LINKS);
		tastenbelegung.put(this.right.getKey(), Action.RECHTS);
		tastenbelegung.put(this.down.getKey(), Action.RUNTER);
		tastenbelegung.put(this.drop.getKey(), Action.GANZ_RUNTER);
		tastenbelegung.put(this.dreh_uzs.getKey(), Action.DREHUNG_UHRZEIGERSINN);
		tastenbelegung.put(this.dreh_euzs.getKey(), Action.DREHUNG_ENTGEGEN_UHRZEIGERSINN);
		tastenbelegung.put(this.pause.getKey(), Action.PAUSE);
		tastenbelegung.put(this.quit.getKey(), Action.QUIT);
		this.playerConfig.setInputManager(new InputManager(
				(InputDevice) this.inputType.getValue(), tastenbelegung));
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		InputDevice device = (InputDevice) this.inputType.getValue();
		switch (device.getType()) {
		case InputDevice.KEYBOARD:
			this.left.setKey(Config.keyboard_left);
			this.right.setKey(Config.keyboard_right);
			this.down.setKey(Config.keyboard_down);
			this.drop.setKey(Config.keyboard_drop);
			this.dreh_uzs.setKey(Config.keyboard_dreh_uzs);
			this.dreh_euzs.setKey(Config.keyboard_dreh_euzs);
			this.pause.setKey(Config.keyboard_pause);
			this.quit.setKey(Config.keyboard_quit);
			break;
		case InputDevice.MOUSE:
			this.left.setKey(Config.mouse_left);
			this.right.setKey(Config.mouse_right);
			this.down.setKey(Config.mouse_down);
			this.drop.setKey(Config.mouse_drop);
			this.dreh_uzs.setKey(Config.mouse_dreh_uzs);
			this.dreh_euzs.setKey(Config.mouse_dreh_euzs);
			this.pause.setKey(Config.mouse_pause);
			this.quit.setKey(Config.mouse_quit);
			break;
		case InputDevice.GAMEPAD:
			this.left.setKey(Config.gamepad_left);
			this.right.setKey(Config.gamepad_right);
			this.down.setKey(Config.gamepad_down);
			this.drop.setKey(Config.gamepad_drop);
			this.dreh_uzs.setKey(Config.gamepad_dreh_uzs);
			this.dreh_euzs.setKey(Config.gamepad_dreh_euzs);
			this.pause.setKey(Config.gamepad_pause);
			this.quit.setKey(Config.gamepad_quit);
			break;
		}
	}
	
}