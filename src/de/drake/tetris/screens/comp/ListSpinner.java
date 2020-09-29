package de.drake.tetris.screens.comp;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

public class ListSpinner extends JSpinner {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public ListSpinner(final Object[] values, final int length) {
		
		this.setModel(new SpinnerListModel(values));
		
		ListEditor editor = (ListEditor) this.getEditor();
		JFormattedTextField field = editor.getTextField();
		field.setColumns(length);
		field.setEditable(false);
		field.setHorizontalAlignment(JTextField.RIGHT);
		
	}

}
