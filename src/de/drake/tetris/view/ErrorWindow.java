package de.drake.tetris.view;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ErrorWindow extends JFrame {
	
	/**
	 * Default serial VersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ErrorWindow(final Throwable e) {
		super("Absturz");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		JTextArea text = new JTextArea(sw.toString());
		this.add(text);
		this.setVisible(true);
	}
	
}