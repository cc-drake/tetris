package de.drake.tetris.screens.comp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DateEditor;
import javax.swing.text.DateFormatter;

public class DateSpinner extends JPanel {
	
	/**
	 * Die Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	private final JSpinner spinner;

	public DateSpinner(final String name, final Color color, final int size) {
		super.setOpaque(false);
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.spinner = new JSpinner();
		SpinnerDateModel model = new SpinnerDateModel();
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		model.setValue(calender.getTime());
		spinner.setModel(model);
		
		DateEditor editor = new DateEditor(spinner, "mm:ss");
		DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		spinner.setEditor(editor);
		
		spinner.setFont(new Font(Font.SERIF, Font.BOLD, size));
		
		JLabel label = new JLabel(name + ":");
		label.setLabelFor(spinner);
		label.setForeground(color);
		label.setFont(new Font(Font.SERIF, Font.PLAIN, size));
		
		super.add(label);
		super.add(spinner);
	}
	
	public Date getValue() {
		return (Date) this.spinner.getValue();
	}
}
