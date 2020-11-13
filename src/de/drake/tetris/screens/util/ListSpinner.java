package de.drake.tetris.screens.util;

import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import de.drake.tetris.config.Config;

public class ListSpinner extends JSpinner {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public ListSpinner(final Object[] values, final int length) {
		
		this.setFont(new Font(Font.SERIF, Font.BOLD, Config.SIZE_TEXT));
		
		this.setModel(new SpinnerListModel(values));
		
		ListEditor editor = (ListEditor) this.getEditor();
		JFormattedTextField field = editor.getTextField();
		field.setColumns(length);
		field.setEditable(false);
		field.setHorizontalAlignment(JTextField.RIGHT);
		
	}

}
