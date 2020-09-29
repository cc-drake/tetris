package de.drake.tetris.input;

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse extends InputDevice implements MouseListener, MouseWheelListener {
	
	private final Key left = new Key(MouseEvent.BUTTON1, "LMB");
	private final Key middle = new Key(MouseEvent.BUTTON2, "CMB");
	private final Key right = new Key(MouseEvent.BUTTON3, "RMB");
	private final Key scrollUp = new Key(-1, "SCD");
	private final Key scrollDown = new Key(0, "SCU");

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Key key;
		if (e.getWheelRotation() == 1) {
			key = this.scrollUp;
		} else {
			key = this.scrollDown;
		}
		this.keyPressed(key);
		this.keyReleased(key);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Key key;
		if (e.getButton() == MouseEvent.BUTTON1)
			key = this.left;
		else if (e.getButton() == MouseEvent.BUTTON2)
			key = this.middle;
		else
			key = this.right;
		this.keyPressed(key);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Key key;
		if (e.getButton() == MouseEvent.BUTTON1)
			key = this.left;
		else if (e.getButton() == MouseEvent.BUTTON2)
			key = this.middle;
		else
			key = this.right;
		this.keyReleased(key);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		this.keyReleased(this.left);
		this.keyReleased(this.middle);
		this.keyReleased(this.right);
		this.keyReleased(this.scrollDown);
		this.keyReleased(this.scrollUp);
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.keyReleased(this.left);
		this.keyReleased(this.middle);
		this.keyReleased(this.right);
		this.keyReleased(this.scrollDown);
		this.keyReleased(this.scrollUp);
	}

	@Override
	public String toString() {
		return "Maus";
	}

}