package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.drake.tetris.config.Player;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.input.InputManager;
import de.drake.tetris.input.Key;
import de.drake.tetris.util.Action;

public class PlayerOptionTable extends OptionTable implements ChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Player player;
	
	private final JTextField name;
	
	private final JSpinner speed, inputType;
	
	private KeyInputField left, right, down, drop, dreh_uzs, dreh_euzs, pause, quit;

	public PlayerOptionTable(final Player player, final Color color, final Color bgcolor, final int size) {
		super(color, bgcolor, size);
		this.player = player;
		
		this.name = new JTextField(player.getName());
		super.addOption("Spielername", name);
		
		this.speed = new NumberSpinner(2., .1, 99., .1);
		super.addOption("Geschwindigkeit", this.speed);
	
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
		this.player.setInitialSpeed((double) this.speed.getValue());
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
		case InputDevice.MOUSE:
			this.left.setKey(new Key(MouseEvent.BUTTON1, "LMB"));
			this.right.setKey(new Key(MouseEvent.BUTTON3, "RMB"));
			this.down.setKey(null);
			this.drop.setKey(new Key(MouseEvent.BUTTON2, "MMB"));
			this.dreh_uzs.setKey(new Key(-1, "SCD"));
			this.dreh_euzs.setKey(new Key(0, "SCU"));
			this.pause.setKey(null);
			this.quit.setKey(null);
			break;
		case InputDevice.KEYBOARD:
			this.left.setKey(new Key(KeyEvent.VK_A, "A"));
			this.right.setKey(new Key(KeyEvent.VK_D, "D"));
			this.down.setKey(new Key(KeyEvent.VK_S, "S"));
			this.drop.setKey(new Key(KeyEvent.VK_SPACE, "Space"));
			this.dreh_uzs.setKey(new Key(KeyEvent.VK_NUMPAD5, "NUM-5"));
			this.dreh_euzs.setKey(new Key(KeyEvent.VK_NUMPAD4, "NUM-4"));
			this.pause.setKey(new Key(KeyEvent.VK_ENTER, "Enter"));
			this.quit.setKey(new Key(KeyEvent.VK_ESCAPE, "Escape"));
			break;
		case InputDevice.GAMEPAD:
			this.left.setKey(new Key(KeyEvent.VK_LEFT, "Links"));
			this.right.setKey(new Key(KeyEvent.VK_RIGHT, "Rechts"));
			this.down.setKey(new Key(KeyEvent.VK_DOWN, "Unten"));
			this.drop.setKey(new Key(KeyEvent.VK_4, "4"));
			this.dreh_uzs.setKey(new Key(KeyEvent.VK_1, "0"));
			this.dreh_euzs.setKey(new Key(KeyEvent.VK_0, "1"));
			this.pause.setKey(new Key(KeyEvent.VK_7, "7"));
			this.quit.setKey(new Key(KeyEvent.VK_6, "6"));
			break;
		}
	}
	
}
