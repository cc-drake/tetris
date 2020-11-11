package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.JTextField;

import de.drake.tetris.config.Player;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.input.InputManager;
import de.drake.tetris.input.Key;
import de.drake.tetris.util.Action;

public class PlayerOptionTable extends OptionTable {
	
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
	
}
