package de.drake.tetris.screens.comp;

import java.util.Calendar;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;

public class TimeSpinner extends JSpinner {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public TimeSpinner() {
		
		SpinnerDateModel model = new SpinnerDateModel();
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		model.setValue(calender.getTime());
		this.setModel(model);
		
		DateEditor editor = new DateEditor(this, "mm:ss");
		DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		this.setEditor(editor);
	}

}
