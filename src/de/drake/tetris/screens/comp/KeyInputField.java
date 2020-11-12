package de.drake.tetris.screens.comp;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import de.drake.tetris.config.Config;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.input.Key;
import de.drake.tetris.input.KeyListener;

public class KeyInputField extends JPanel implements ActionListener, KeyListener {
	
	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private final JSpinner inputType;
	
	private final JTextField description;
	
	private Key key;

	public KeyInputField(final JSpinner inputType) {
		super();
		this.inputType = inputType;
		super.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.description = new JTextField();
		this.description.setFont(new Font(Font.SERIF, Font.BOLD, Config.textSize));
		this.description.setEditable(false);
		this.description.setFocusable(false);
		super.add(this.description);
		JButton button = ComponentFactory.createButton("X", this);
		super.add(button);
		InputDevice.registerInputDevices(button);
	}
	
	public void setKey(final Key key) {
		this.key = key;
		if (key == null)
			this.description.setText("");
		else
			this.description.setText(this.key.getDescription());
	}
	
	public Key getKey() {
		return this.key;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		InputDevice.removeInputManagers();
		InputDevice inputDevice = (InputDevice) this.inputType.getValue();
		inputDevice.addKeyListener(this);
	}

	@Override
	public void keyPressed(final Key key) {
		this.setKey(key);
		InputDevice.removeInputManagers();
	}

	@Override
	public void keyReleased(final Key key) {
	}

}