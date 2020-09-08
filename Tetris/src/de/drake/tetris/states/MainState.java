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
public class MainState extends GameState implements ActionListener {
	
	/**
	 * Das Panel, in dem gezeichnet wird.
	 */
	private final JPanel jPanel;
	
	/**
	 * Erstellt einen neuen MainState.
	 */
	MainState() {
		
		this.jPanel = new JPanel();
		this.jPanel.setBackground(Color.black);
		this.jPanel.setLayout(new BorderLayout());
		this.jPanel.add(new LogoScreen(), BorderLayout.CENTER);
		
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.black);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
				JButton start = new JButton("Spiel starten");
				start.addActionListener(this);
				buttonPanel.add(start);
				
				JButton end = new JButton("Beenden");
				end.addActionListener(this);
				buttonPanel.add(end);
				
			this.jPanel.add(buttonPanel, BorderLayout.SOUTH);
			
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public JPanel getJPanel() {
		return this.jPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Spiel starten") {
			GameStateManager.setState(GameStateManager.playState);
		} else if (e.getActionCommand() == "Beenden") {
			System.exit(0);
		}
	}
}