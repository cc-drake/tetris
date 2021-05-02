package de.drake.tetris.view.screens.util;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.drake.tetris.config.Config;
import de.drake.tetris.config.PlayerConfig;
import de.drake.tetris.input.InputDevice;

public class PlayerConfigTable extends OptionTable implements ChangeListener {
	
	/**
	 * Die Default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	private final PlayerConfig playerConfig;
	
	private final JTextField name;
	
	private final NumberSpinner initialSpeed;
	
	private final ListSpinner inputType;
	
	private KeyInputField left, right, down, drop, dreh_uzs, dreh_euzs, pause, quit;

	public PlayerConfigTable(final PlayerConfig playerConfig) {
		super();
		this.playerConfig = playerConfig;
		
		this.name = ComponentFactory.createJTextField(playerConfig.getName());
		super.addOption("Spielername", name);
		
		this.initialSpeed = new NumberSpinner(playerConfig.getInitialSpeed(), 0., Config.FPS, .1);
		super.addOption("Fallgeschwindigkeit", this.initialSpeed);
	
		this.inputType = new ListSpinner(InputDevice.allInputdevices.toArray(), 7, playerConfig.getInputDevice());
		this.inputType.addChangeListener(this);
		super.addOption("Eingabegerät", this.inputType);
		
		this.left = new KeyInputField(inputType, playerConfig.getLeft());
		super.addOption("Links", this.left);
		
		this.right = new KeyInputField(inputType, playerConfig.getRight());
		super.addOption("Rechts", this.right);
		
		this.down = new KeyInputField(inputType, playerConfig.getDown());
		super.addOption("Runter", this.down);
		
		this.drop = new KeyInputField(inputType, playerConfig.getDrop());
		super.addOption("Absetzen", this.drop);
		
		this.dreh_uzs = new KeyInputField(inputType, playerConfig.getDreh_uzs());
		super.addOption("Drehen (UZS)", this.dreh_uzs);
		
		this.dreh_euzs = new KeyInputField(inputType, playerConfig.getDreh_euzs());
		super.addOption("Drehen (eUZS)", this.dreh_euzs);
		
		this.pause = new KeyInputField(inputType, playerConfig.getPause());
		super.addOption("Pause", this.pause);
		
		this.quit = new KeyInputField(inputType, playerConfig.getQuit());
		super.addOption("Beenden", this.quit);

	}
	
	public void applyPlayerConfig() {
		if (this.name.getText().isEmpty()) {
			this.playerConfig.setName("Namenloser Spieler");
		} else {
			this.playerConfig.setName(this.name.getText());
		}
		this.playerConfig.setInitialSpeed(this.initialSpeed.getDoubleValue());
		this.playerConfig.setInputDevice((InputDevice) this.inputType.getValue());
		this.playerConfig.setLeft(this.left.getKey());
		this.playerConfig.setRight(this.right.getKey());
		this.playerConfig.setDown(this.down.getKey());
		this.playerConfig.setDrop(this.drop.getKey());
		this.playerConfig.setDreh_uzs(this.dreh_uzs.getKey());
		this.playerConfig.setDreh_euzs(this.dreh_euzs.getKey());
		this.playerConfig.setPause(this.pause.getKey());
		this.playerConfig.setQuit(this.quit.getKey());
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