package de.drake.tetris.screens.comp;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

public class DoubleSpinner extends JSpinner {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public DoubleSpinner(final double initialValue, final double maxValue, final double step) {
		
		this.setModel(new SpinnerNumberModel(initialValue, 0., maxValue, step));
		
		String maxString = Double.toString(maxValue);
		int vorkommastellen;
		if (maxString.contains(".")) {
			vorkommastellen = maxString.substring(0, maxString.indexOf(".")).length();
		} else {
			vorkommastellen = maxString.length();
		}
		
		String stepString = Double.toString(step);
		int nachkommastellen;
		if (stepString.endsWith(".0")) {
			nachkommastellen = 0;
		} else {
			nachkommastellen = stepString.substring(stepString.indexOf(".") + 1).length();
		}
		
		String pattern = "";
		for (int i = 0; i < vorkommastellen - 1; i++) {
			pattern += "#";
		}
		pattern += "0";
		for (int i = 0; i < nachkommastellen; i++) {
			if (i == 0)
				pattern += ".";
			pattern += "0";
		}
		
		NumberEditor editor = new NumberEditor(this, pattern);
		JFormattedTextField field = editor.getTextField();
		field.setColumns(vorkommastellen + nachkommastellen - 1);
		NumberFormatter formatter = (NumberFormatter) field.getFormatter();
		formatter.setAllowsInvalid(false);
		this.setEditor(editor);
		
	}

}
