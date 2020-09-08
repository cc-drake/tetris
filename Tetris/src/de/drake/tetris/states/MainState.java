package de.drake.tetris.states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.drake.tetris.view.LogoScreen;

/**
 * Der MainState besteht aus dem Startbildschirm.
 */
public class MainState extends State implements ActionListener {
	
	/**
	 * Der Screen, der im Display angezeigt wird.
	 */
	private final JPanel screen;
	
	/**
	 * Erstellt einen neuen MainState.
	 */
	MainState() {
		
		this.screen = new JPanel();
		this.screen.setBackground(Color.black);
		this.screen.setLayout(new BorderLayout());
		this.screen.add(new LogoScreen(), BorderLayout.CENTER);
		
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.black);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
				JButton start = new JButton("Spiel starten");
				start.addActionListener(this);
				buttonPanel.add(start);
				
				JButton end = new JButton("Beenden");
				end.addActionListener(this);
				buttonPanel.add(end);
				
			this.screen.add(buttonPanel, BorderLayout.SOUTH);
			
	}
	
	@Override
	public void tick() {
	}

	@Override
	public JPanel getScreen() {
		return this.screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Spiel starten") {
			State.setCurrentState(new GameState());
		} else if (e.getActionCommand() == "Beenden") {
			System.exit(0);
		}
	}
}