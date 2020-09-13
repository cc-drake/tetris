package de.drake.tetris.screens.comp;

import java.util.Calendar;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;

public class TimeSpinner extends JSpinner {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public TimeSpinner(final int valueSec) {
		
		SpinnerDateModel model = new SpinnerDateModel();
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.MINUTE, valueSec / 60);
		calender.set(Calendar.SECOND, valueSec % 60);
		model.setValue(calender.getTime());
		this.setModel(model);
		
		DateEditor editor = new DateEditor(this, "mm:ss");
		JFormattedTextField field = editor.getTextField();
		field.setHorizontalAlignment(JTextField.RIGHT);
		DateFormatter formatter = (DateFormatter) field.getFormatter();
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		this.setEditor(editor);
	}

}
