package de.drake.tetris.screens.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import de.drake.tetris.config.Config;
import de.drake.tetris.input.InputDevice;
import de.drake.tetris.input.util.Key;
import de.drake.tetris.input.util.KeyListener;

public class KeyInputField extends JPanel implements ActionListener, KeyListener, FocusListener {
	
	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private final String clear = "Clear";
	private final String edit = "Edit";
	
	private final Color bgColor = new Color(238, 238, 238);
	
	private final JSpinner inputType;
	
	private final JTextField description;
	
	private Key key;

	public KeyInputField(final JSpinner inputType, final Key initialValue) {
		super();
		this.inputType = inputType;
		super.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.description = new JTextField();
		this.description.setFont(new Font(Font.SERIF, Font.BOLD, Config.SIZE_TEXT));
		this.description.setEditable(false);
		this.description.setFocusable(false);
		super.add(this.description);
		JButton editButton = ComponentFactory.createButton("Ändern", this);
		editButton.setActionCommand(this.edit);
		InputDevice.registerInputDevices(editButton);
		editButton.addFocusListener(this);
		super.add(editButton);
		JButton clearButton = ComponentFactory.createButton("Leeren", this);
		clearButton.setActionCommand(this.clear);
		super.add(clearButton);
		this.setKey(initialValue);
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
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == this.edit) {
			InputDevice inputDevice = (InputDevice) this.inputType.getValue();
			inputDevice.addKeyListener(this);
			this.description.setBackground(Color.red);
			return;
		}
		if (e.getActionCommand() == this.clear) {
			this.setKey(null);
			return;
		}
	}

	@Override
	public void keyPressed(final Key key) {
		this.setKey(key);
		this.description.setBackground(this.bgColor);
		InputDevice.removeInputManagers();
	}

	@Override
	public void keyReleased(final Key key) {
	}


	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.description.setBackground(this.bgColor);
		InputDevice.removeInputManagers();
	}

}